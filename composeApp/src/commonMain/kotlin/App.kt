import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.navigator.Navigator
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.screen.MainScreen
import ui.theme.GridDarkColorScheme
import ui.theme.GridLightColorScheme
import ui.theme.GridTypography

@Composable
@Preview
fun App() {
    var isDarkThemeEnabled by remember { mutableStateOf(false) }
    MaterialTheme(
        colorScheme = if(isDarkThemeEnabled) GridDarkColorScheme else GridLightColorScheme,
        typography = GridTypography
    ) {
        Navigator(MainScreen { isDarkThemeEnabled = isDarkThemeEnabled.not() })
    }
}