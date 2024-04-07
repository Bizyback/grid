package util

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp

/**
 * project : grid
 * date    : Friday 05/04/2024
 * user    : mambo
 * email   : mambobryan@gmail.com
 */

data class ScreenSizeInfo(val hPX: Int, val wPX: Int, val hDP: Dp, val wDP: Dp)

@Composable
expect fun getScreenSizeInfo(): ScreenSizeInfo
