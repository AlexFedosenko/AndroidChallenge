

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

import java.util.*

@Preview
@Composable
fun CalendarMonthItemWidgetPreview1() {
    CalendarMonthItemWidget(
        month = CalendarMonthState("Jan", Calendar.JANUARY)
    )
}

@Preview
@Composable
fun CalendarMonthItemWidgetPreview2() {
    CalendarMonthItemWidget(
        month = CalendarMonthState("Feb", Calendar.FEBRUARY),
        selected = true
    )
}

@Preview
@Composable
fun CalendarMonthItemWidgetPreview3() {
    CalendarMonthItemWidget(
        month = CalendarMonthState("Mar", Calendar.MARCH),
        enabled = false
    )
}

@Composable
fun CalendarMonthItemWidget(
    modifier: Modifier = Modifier,
    month: CalendarMonthState,
    selected: Boolean = false,
    enabled: Boolean = true,
    onMonthSelected: (CalendarMonthState) -> Unit =  {}
) {

    var itemModifier = modifier
        .background(color = if (selected) AppTheme.colors.primary else AppTheme.colors.surface)
        .width(AppTheme.dimensions.calendarMonthItemWidth)
        .height(AppTheme.dimensions.calendarMonthItemHeight)
    if (enabled) {
        itemModifier =  itemModifier.clickableSingle { onMonthSelected.invoke(month) }
    }
    Box(
        modifier = itemModifier,
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = month.name.uppercase(),
            style = AppTheme.typography.paragraph,
            color = if (enabled && selected) AppTheme.colors.defaultButtonText
            else if (enabled) AppTheme.colors.textPrimary
            else AppTheme.colors.textSecondary2
        )
    }

}