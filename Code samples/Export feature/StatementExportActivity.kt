

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable


class StatementExportActivity : AuthorizedActivity<StatementExportViewModel>() {

    override fun injectDependencies() {
        injector().financialAccountInjector().inject(this)
        viewModel = injectViewModel(viewModelFactory)
    }

    @OptIn(ExperimentalFoundationApi::class)
    override fun composableContent(): @Composable BoxScope.() -> Unit = {
        ComposableContent {
            StatementExportWidget(
                timeFromLiveData = viewModel.timeFromLiveData,
                timeToLiveData = viewModel.timeToLiveData,
                dateSelectedLiveData = viewModel.dateSelectedLiveData,
                onFromDateClick = viewModel::onFromDateClick,
                onToDateClick = viewModel::onToDateClick,
                onCloseClick = ::finishAfterTransition,
                onExportClick = viewModel::onExportClick,
            )
        }
    }

    override fun observeViewModel() {
        super.observeViewModel()
        observe(viewModel.event) {
            when (it) {
                is StatementExportEvent.ShowFromDateDialog ->  {
                    showFromDateDialog()
                }
                is StatementExportEvent.ShowToDateDialog -> {
                    showToDateDialog()
                }
                is StatementExportEvent.ShowFailedExportDialog -> {
                    showUncompletedActionDialog()
                }
                is StatementExportEvent.Share -> {
                    shareFile(it.filename, viewModel.getStatementFileType())
                }
            }
        }.exhaustive
    }

    private fun showFromDateDialog() {
        CalendarMonthPickerDialog.newInstance(
            selectedDate = viewModel.fromDate,
            minDate = viewModel.getMinFromDate(),
            maxDate = viewModel.getMaxFromDate(),
            onDateSelected = viewModel::onFromDateChanged,
            onCancel = viewModel::onCancelCalendarMonthPicker,
        ).show(supportFragmentManager, "Calendar Month Picker")
    }

    private fun showToDateDialog() {
        CalendarMonthPickerDialog.newInstance(
            selectedDate = viewModel.toDate,
            minDate = viewModel.getMinToDate(),
            maxDate = viewModel.getMaxToDate(),
            onDateSelected = viewModel::onToDateChanged,
            onCancel = viewModel::onCancelCalendarMonthPicker,
        ).show(supportFragmentManager, "Calendar Month Picker")
    }

    private fun showUncompletedActionDialog() {
        UncompletedActionDialog.newInstance(
            onFinishClick = { finishAfterTransition() },
            buttonTitle = getString(R.string.general_close)
        ).show(supportFragmentManager, null)
    }
}

fun Activity.shareFile(filePath: String, fileType: String) {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = fileType
        flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        flags = Intent.FLAG_ACTIVITY_NEW_TASK
        val fileURI = FileProvider.getUriForFile(
            applicationContext,
            applicationContext.packageName + ".provider",
            File(filePath)
        )
        putExtra(Intent.EXTRA_STREAM, fileURI)
    }
    startActivity(shareIntent)
}