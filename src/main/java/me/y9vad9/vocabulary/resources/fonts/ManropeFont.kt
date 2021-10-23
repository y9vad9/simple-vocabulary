package me.y9vad9.vocabulary.resources.fonts

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import me.y9vad9.vocabulary.R

private val manropeBold = Font(R.font.manrope_bold, FontWeight.Bold)
private val manropeExtraBold = Font(R.font.manrope_extrabold, FontWeight.ExtraBold)
private val manropeSemiBold = Font(R.font.manrope_semibold, FontWeight.SemiBold)
private val manropeMedium = Font(R.font.manrope_medium, FontWeight.Medium)
private val manropeLight = Font(R.font.manrope_light, FontWeight.Light)
private val manropeExtraLight = Font(R.font.manrope_extralight, FontWeight.ExtraLight)
private val manropeRegular = Font(R.font.manrope_regular, FontWeight.Normal)

val Manrope = FontFamily(
    manropeBold, manropeExtraBold, manropeExtraLight, manropeMedium,
    manropeRegular, manropeLight, manropeSemiBold
)