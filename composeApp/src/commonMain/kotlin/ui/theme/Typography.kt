package ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import grid.composeapp.generated.resources.Res
import grid.composeapp.generated.resources.sans_bold
import grid.composeapp.generated.resources.sans_medium
import grid.composeapp.generated.resources.sans_regular
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.Font

/**
 * project : grid
 * date    : Saturday 06/04/2024
 * user    : mambo
 * email   : mambobryan@gmail.com
 */
@OptIn(ExperimentalResourceApi::class)
val GridTypography: Typography
    @Composable
    get() {
        val Sans = FontFamily(
            Font(Res.font.sans_bold, FontWeight.Bold),
            Font(Res.font.sans_medium, FontWeight.Medium),
            Font(Res.font.sans_regular, FontWeight.Normal),
        )

        return Typography(
            displayLarge = TextStyle(
                fontFamily = Sans,
                fontWeight = FontWeight.SemiBold,
                fontSize = 52.sp,
            ),
            displayMedium = TextStyle(
                fontFamily = Sans,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            ),
            displaySmall = TextStyle(
                fontFamily = Sans,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
            ),
            titleLarge = TextStyle(
                fontFamily = Sans,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
            ),
            titleMedium = TextStyle(
                fontFamily = Sans,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            ),
            titleSmall = TextStyle(
                fontFamily = Sans,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
            ),
            bodyLarge = TextStyle(
                fontFamily = Sans,
                fontWeight = FontWeight.W500,
                fontSize = 16.sp,
            ),
            bodyMedium = TextStyle(
                fontFamily = Sans,
                fontWeight = FontWeight.W400,
                fontSize = 14.sp,
            ),
            bodySmall = TextStyle(
                fontFamily = Sans,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp
            ),
            labelLarge = TextStyle(
                fontFamily = Sans,
                fontWeight = FontWeight.W400,
                fontSize = 15.sp,
            ),
            labelMedium = TextStyle(
                fontFamily = Sans,
                fontWeight = FontWeight.Normal,
                fontSize = 8.sp
            ),
            labelSmall = TextStyle(
                fontFamily = Sans,
                fontWeight = FontWeight.W400,
                fontSize = 12.sp
            )
        )

    }