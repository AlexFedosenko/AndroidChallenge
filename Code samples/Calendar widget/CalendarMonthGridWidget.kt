

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.tooling.preview.Preview

import java.text.DateFormatSymbols
import java.util.*

@Preview
@Composable
fun CalendarMonthGridWidgetPreview() {
    CalendarMonthGridWidget(
        monthStateList = (DateFormatSymbols().shortMonths).toList().filterNot { it.isBlank() }
            .mapIndexed { index, month ->
                CalendarMonthState(name = month, index = index)
            },
        selectedMonth = CalendarMonthState("Mar", Calendar.MARCH),
        selectedYear = 2022,
        minMonth = Calendar.FEBRUARY,
        maxMonth = Calendar.OCTOBER,
    )
}

@Composable
fun CalendarMonthGridWidget(
    modifier: Modifier = Modifier.getDefaultCalendarMonthModifier(),
    monthStateList: List<CalendarMonthState>,
    selectedMonth: CalendarMonthState,
    selectedYear: Int,
    shownYear: Int = selectedYear,
    minMonth: Int = Calendar.JANUARY,
    maxMonth: Int = Calendar.DECEMBER,
    minYear: Int = selectedYear,
    maxYear: Int = selectedYear,
    itemsInRow: Int = DEFAULT_ITEMS_IN_ROW,
    onMonthSelected: (CalendarMonthState) -> Unit = {}
) {

    LazyColumn(
        modifier = modifier,
    ) {
        items(items = monthStateList.chunked(itemsInRow)) { rowItems ->
            Row(
                modifier = Modifier.wrapContentSize(),
                horizontalArrangement = Arrangement.Center
            ) {
                for (item in rowItems) {
                    CalendarMonthItemWidget(
                        month = item,
                        selected = item == selectedMonth && selectedYear == shownYear,
                        enabled = isMonthEnabled(
                            yearRange = minYear..maxYear,
                            year = shownYear,
                            monthRange = minMonth..maxMonth,
                            month = item.index
                        ),
                        onMonthSelected = onMonthSelected,
                    )
                }
            }
        }
    }
}

private fun isMonthEnabled(
    yearRange: IntRange,
    year: Int,
    monthRange: IntRange,
    month: Int
): Boolean {

//    if (year !in yearRange) {
//        return false
//    } else {
//    return year >= yearRange.first && month >= monthRange.first && year <= yearRange.last && month <= monthRange.last
//    }

    if (year !in yearRange) {
        return false
    } else if (yearRange.count() == 1) {
        return month in monthRange
    } else if (yearRange.first == year){
        return month >= monthRange.first
    } else if (yearRange.last == year) {
        return month <= monthRange.last
    }

    return true
}

private fun Modifier.getDefaultCalendarMonthModifier(): Modifier = composed {
    this.background(AppTheme.colors.surface)
}

private const val DEFAULT_ITEMS_IN_ROW = 4


