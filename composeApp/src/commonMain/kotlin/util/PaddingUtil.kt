package util

import androidx.compose.ui.unit.Dp

/**
 * project : grid
 * date    : Monday 08/04/2024
 * user    : mambo
 * email   : mambobryan@gmail.com
 */
fun getPadding(value: Dp) = when (platform) {
    Platform.Android -> value.times(2)
    Platform.iOS -> value
}