

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import java.time.OffsetTime
import java.util.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext
import kotlin.math.min

class StatementExportViewModel @Inject constructor(
    baseCoroutineContext: CoroutineContext,
    appServicesProvider: AppServicesProvider,
    logger: Logger,
    private val financialAccountEntity: FinancialAccountEntity,
    private val getFinancialAccountStatementUseCase: GetFinancialAccountStatementUseCase,
) : AuthorizedViewModel<StatementExportEvent>(
    baseCoroutineContext,
    appServicesProvider,
    logger
) {

    var fromDate: Date = DateUtils.getDateBeforeMonths(Date(), 1)
    var toDate: Date = DateUtils.dateOfDayInMonth(
        fromDate,
        DayOfMonthSelectionType.LAST_DAY,
        OffsetTime.MAX
    )

    private var timeFromState: TextInputState = createTimeFromInputState()
    private var _timeFromLiveData = MutableLiveData(timeFromState)
    val timeFromLiveData: LiveData<TextInputState> = _timeFromLiveData

    private var timeToState: TextInputState = createToFromInputState()
    private var _timeToLiveData = MutableLiveData(timeToState)
    val timeToLiveData: LiveData<TextInputState> = _timeToLiveData

    private var _dateSelectedLiveData = MutableLiveData<Any>()
    val dateSelectedLiveData: LiveData<Any> = _dateSelectedLiveData

    private var exportStatementFailuresCounter = 0

    fun onFromDateClick() {
        StatementExportEvent.ShowFromDateDialog.event()
    }

    fun onToDateClick() {
        StatementExportEvent.ShowToDateDialog.event()
    }

    fun onFromDateChanged(date: Date) {
        fromDate = DateUtils.dateOfDayInMonth(
            date,
            DayOfMonthSelectionType.FIRST_DAY,
            OffsetTime.MIN
        )
        timeFromState = timeFromState.copy(
            value = DateFormatter.monthShortNameYearFormat().format(date)
        )
        _timeFromLiveData.postValue(timeFromState)
        _dateSelectedLiveData.postValue(Any())
        if (toDate.before(fromDate)) {
            onToDateChanged(fromDate)
        }
    }

    fun onToDateChanged(date: Date) {
        toDate = DateUtils.dateOfDayInMonth(
            date,
            DayOfMonthSelectionType.LAST_DAY,
            OffsetTime.MAX
        )
        timeToState = timeToState.copy(
            value = DateFormatter.monthShortNameYearFormat().format(date)
        )
        _timeToLiveData.postValue(timeToState)
        _dateSelectedLiveData.postValue(Any())
    }

    fun onCancelCalendarMonthPicker() {
        _dateSelectedLiveData.postValue(Any())
    }

    fun getMinFromDate(): Date = financialAccountEntity.createdAt

    fun getMaxFromDate(): Date =
        DateUtils.getDateBeforeMonths(Date(), minusMonths = 1)

    fun getMinToDate(): Date =
        fromDate

    fun getMaxToDate(): Date =
        Date(
            min(
                DateUtils.getDateBeforeMonths(Date(), minusMonths = 1).time,
                DateUtils.getDateAfterMonths(fromDate, MAX_STATEMENT_TIME_RANGE_IN_MONTHS).time
            )
        )

    fun onExportClick() {
        val params = GetFinancialAccountStatementParams(
            financialAccountId = financialAccountEntity.id,
            startDate = DateUtils.dateInHardcodedZone(fromDate),
            endDate = DateUtils.dateInHardcodedZone(toDate),
            timeZone = TimeZone.getTimeZone(HARDCODED_ZONE),
        )
        getFinancialAccountStatementUseCase.invoke(
            params,
            onResult = {
                if (it.isNotBlank()) {
                    StatementExportEvent.Share(it).event()
                }
            },
            onFailure = {
                proceedWithFailure(it)
            },
            showLoading = true
        )
    }

    fun getStatementFileType(): String = STATEMENT_FILE_TYPE

    private fun proceedWithFailure(failure: Failure) {
        exportStatementFailuresCounter++
        if (exportStatementFailuresCounter >= MAX_FAILURES_COUNT) {
            StatementExportEvent.ShowFailedExportDialog.event()
        } else {
            handleNonGenericFailure(failure)
        }
    }

    private fun createTimeFromInputState() =
        TextInputState(
            label = getString(R.string.general_from),
            value = DateFormatter.monthShortNameYearFormat().format(fromDate),
            readOnly = true,
            showClearOption = false,
        )

    private fun createToFromInputState() =
        TextInputState(
            label = getString(R.string.general_to),
            value = DateFormatter.monthShortNameYearFormat().format(toDate),
            readOnly = true,
            showClearOption = false,
        )

    companion object {
        private const val MAX_FAILURES_COUNT = 3
        private const val MAX_STATEMENT_TIME_RANGE_IN_MONTHS = 12L

        private const val STATEMENT_FILE_TYPE = "application/pdf"
    }
}

sealed class StatementExportEvent {

    object ShowFromDateDialog : StatementExportEvent()

    object ShowToDateDialog : StatementExportEvent()

    object ShowFailedExportDialog : StatementExportEvent()

    data class Share(
        val filename: String
    ) : StatementExportEvent()
}
