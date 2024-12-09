package com.luisgmr.ifsc.hospital.themes

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val LightColorPalette = lightColors(
    primary = Color(0xff575ceb),
    primaryVariant = Color(0xFF3700B3),
    secondary = Color(0xffd7d7d7),
    background = Color(0xffeeeeee),
    surface = Color(0xFFFFFFFF),
    onPrimary = Color.White,
    onSecondary = Color(0xffe0e0e0),
    onBackground = Color.Black,
    onSurface = Color.Black,
    error = Color(0xffa61111),
)

private val DarkColorPalette = darkColors(
    primary = Color(0xff245379),
    primaryVariant = Color(0xFF3700B3),
    secondary = Color(0xff949494),
    background = Color(0xFF121212),
    surface = Color(0xFF121212),
    onPrimary = Color.White,
    onSecondary = Color(0xffe0e0e0),
    onBackground = Color.White,
    onSurface = Color.White
)

val HospitalFontFamily = FontFamily(
    Font(resource = "fonts/Ubuntu-Light.ttf", weight = FontWeight.Light),
    Font(resource = "fonts/rethink_sans_regular.ttf", weight = FontWeight.Normal),
    Font(resource = "fonts/rethink_sans_medium.ttf", weight = FontWeight.Medium),
    Font(resource = "fonts/rethink_sans_semi_bold.ttf", weight = FontWeight.SemiBold),
    Font(resource = "fonts/rethink_sans_bold.ttf", weight = FontWeight.Bold),
    Font(resource = "fonts/rethink_sand_extra_bold.ttf", weight = FontWeight.ExtraBold)
)

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(8.dp),
    large = RoundedCornerShape(16.dp)
)

val Typography = Typography(
    h1 = TextStyle(
        fontFamily = HospitalFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp
    ),
    h2 = TextStyle(
        fontFamily = HospitalFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp
    ),
    h3 = TextStyle(
        fontFamily = HospitalFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    body1 = TextStyle(
        fontFamily = HospitalFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = HospitalFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    overline = TextStyle(
        fontFamily = HospitalFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    button = TextStyle(
        fontFamily = HospitalFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = HospitalFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp
    )

)



@Composable
fun HospitalTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}