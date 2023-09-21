

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun CalendarMonthPickerHeaderWidgetPreview() {
    CalendarMonthPickerHeaderWidget(
        currentYear = 2022
    )
}

@Composable
fun CalendarMonthPickerHeaderWidget(
    modifier: Modifier = Modifier,
    currentYear: Int,
    onYearIncreased: () -> Unit = {},
    onYearDecreased: () -> Unit = {},
) {

    Row(
        modifier = modifier
            .width(AppTheme.dimensions.calendarHeaderWidth)
            .height(AppTheme.dimensions.calendarHeaderHeight)
            .background(AppTheme.colors.surface)
            .padding(
                horizontal = AppTheme.dimensions.paddingXXXLarge
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconWidget(
            modifier = Modifier
                .size(
                    width = AppTheme.dimensions.iconSizeLarge,
                    height = AppTheme.dimensions.iconSizeLarge,
                )
                .clickable { onYearDecreased.invoke() },
            imageResource = R.drawable.ic_chevron_left,
            tintColor = AppTheme.colors.icon,
        )

        Text(
            modifier = Modifier
                .weight(1f),
            text = currentYear.toString(),
            style = AppTheme.typography.subtitle1,
            color = AppTheme.colors.textPrimary,
            textAlign = TextAlign.Center,
        )

        IconWidget(
            modifier = Modifier
                .size(
                    width = AppTheme.dimensions.iconSizeLarge,
                    height = AppTheme.dimensions.iconSizeLarge,
                )
                .clickable { onYearIncreased.invoke() },
            imageResource = R.drawable.ic_chevron_right,
            tintColor = AppTheme.colors.icon,
        )
    }
}
