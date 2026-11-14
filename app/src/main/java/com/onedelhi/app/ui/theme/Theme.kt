package com.onedelhi.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object OneColors {
    val Maroon = Color(0xFFA51C30)
    val MaroonDark = Color(0xFF7A1424)
    val Red = Color(0xFFD0342C)
    val Footer = Color(0xFFB5302A)
    val StampRed = Color(0xFFD0453C)
    val Green = Color(0xFF1FA463)
    val ScreenBg = Color(0xFFF0F0F0)
    val HeaderBlue = Color(0xFFBFD4F7)
    val Orange = Color(0xFFF58B3C)
    val Grey = Color(0xFF8A8A8A)
    val ChipGrey = Color(0xFFAFAFAF)
}

private val Scheme = lightColorScheme(
    primary = OneColors.Red,
    secondary = OneColors.Green,
    background = OneColors.ScreenBg,
    surface = Color.White
)

@Composable
fun OneDelhiTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = Scheme, content = content)
}
