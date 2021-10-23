package me.y9vad9.vocabulary.resources.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val lightColors = lightColors()

private val darkColors = darkColors()

@Composable
fun SimpleVocabularyTheme(isDarkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (isDarkTheme) darkColors else lightColors
    rememberSystemUiController().apply {
        if (isDarkTheme) {
            setNavigationBarColor(colors.surface)
            setStatusBarColor(colors.surface)
        } else {
            setNavigationBarColor(Color.White)
            setStatusBarColor(colors.primaryVariant)
        }
    }
    MaterialTheme(colors = colors, content = content)
}