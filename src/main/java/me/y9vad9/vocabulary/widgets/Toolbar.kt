package me.y9vad9.vocabulary.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import me.y9vad9.vocabulary.resources.fonts.Manrope

@Composable
fun VocabularyToolbar(title: String, backButton: ImageVector = Icons.Rounded.ArrowBack, onBackPressed: () -> Unit) = TopAppBar(
    modifier = Modifier.fillMaxWidth(),
    title = { Text(title, fontFamily = Manrope, fontWeight = FontWeight.ExtraBold) },
    navigationIcon = {
        IconButton(onClick = onBackPressed) {
            Image(
                imageVector = backButton, contentDescription = "Go back", colorFilter = ColorFilter.tint(Color.White)
            )
        }
    }
)