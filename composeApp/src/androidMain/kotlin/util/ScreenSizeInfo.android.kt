package util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
actual fun getScreenSizeInfo(): ScreenSizeInfo {
    val density = LocalDensity.current
    val config = LocalConfiguration.current
    val hDp = config.screenHeightDp.dp
    val wDp = config.screenWidthDp.dp

    return remember(density, config) {
        ScreenSizeInfo(
            hPX = with(density) { hDp.roundToPx() },
            wPX = with(density) { wDp.roundToPx() },
            hDP = hDp,
            wDP = wDp
        )
    }
}