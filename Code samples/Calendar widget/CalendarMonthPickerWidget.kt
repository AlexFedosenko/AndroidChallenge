

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

import java.text.DateFormatSymbols
import java.util.*

@Preview
@Composable
fun CalendarMonthPickerWidgetPreview() {
    CalendarMonthPickerWidget(
        minDate = Date(122, 3, 2),
        maxDate = Date(122, 9, 22),
    )
}

@Composable
fun CalendarMonthPickerWidget(
    modifier: Modifier = Modifier,
    minDate: Date? = null,
    maxDate: Date? = null,
    preselectedDate: Date = Date(),
    locale: Locale = Locale.getDefault(),
    onDateSelected: (Date) -> Unit = {},
) {

    val months = (DateFormatSymbols(locale).shortMonths).toList().filterNot { it.isBlank() }
    val monthStateList = months.mapIndexed { index, month ->
        CalendarMonthState(name = month, index = index)
    }

    val calendarDate = Calendar.getInstance()
    calendarDate.time = preselectedDate
    val preselectedMonth = calendarDate.get(Calendar.MONTH)
    var preselectedYear = calendarDate.get(Calendar.YEAR)
    var selectedMonth by remember {
        mutableStateOf(monthStateList[preselectedMonth])
    }
    var selectedYear by remember {
        mutableStateOf(preselectedYear)
    }

    var shownYear by remember {
        mutableStateOf(preselectedYear)
    }

    var minYear = preselectedYear
    var minMonth = Calendar.JANUARY
    var maxYear = preselectedYear
    var maxMonth = Calendar.DECEMBER

    minDate?.let {
        val calendarMin = Calendar.getInstance()
        calendarMin.time = minDate
        minMonth = calendarMin.get(Calendar.MONTH)
        minYear = calendarMin.get(Calendar.YEAR)
    }
    maxDate?.let {
        val calendarMax = Calendar.getInstance()
        calendarMax.time = maxDate
        maxMonth = calendarMax.get(Calendar.MONTH)
        maxYear = calendarMax.get(Calendar.YEAR)
    }

    Column(
        modifier = modifier
            .background(AppTheme.colors.surface)
            .wrapContentSize()
            .padding(
                start = AppTheme.dimensions.paddingXLarge,
                end = AppTheme.dimensions.paddingXLarge,
                bottom = AppTheme.dimensions.paddingXLarge,
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CalendarMonthPickerHeaderWidget(
            currentYear = shownYear,
            onYearDecreased = { shownYear -= 1 },
            onYearIncreased = { shownYear += 1 }
        )

        CalendarMonthGridWidget(
            monthStateList = monthStateList,
            selectedMonth = selectedMonth,
            selectedYear = selectedYear,
            shownYear = shownYear,
            minYear = minYear,
            maxYear = maxYear,
            minMonth = minMonth,
            maxMonth = maxMonth,
            onMonthSelected = {
                selectedMonth = it
                selectedYear = shownYear
                onDateSelected(it.mapToDate(shownYear))
            }
        )
    }
}
