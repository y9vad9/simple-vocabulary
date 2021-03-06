package me.y9vad9.vocabulary.screens.groups.items.add

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import me.y9vad9.vocabulary.widgets.VocabularyToolbar

@OptIn(ExperimentalUnitApi::class)
@Composable
fun AddTranslationView(viewModel: AddTranslationViewModel) = Scaffold(
    modifier = Modifier.fillMaxSize(),
    topBar = { VocabularyToolbar(title = "New word", onBackPressed = { viewModel.onBackPressed() }) }
) {
    val words = viewModel.words.collectAsState()
    val variants = viewModel.variants.collectAsState()
    Column(modifier = Modifier.padding(8.dp)) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            value = words.value,
            onValueChange = { value -> viewModel.processWords(value) },
            singleLine = true,
            label = { Text("Word") }
        )

        Text(
            text = "You can write multiple words separated by \",\" separator.",
            fontSize = TextUnit(12f, TextUnitType.Sp),
            fontWeight = FontWeight.Light
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            value = variants.value,
            onValueChange = { value -> viewModel.processVariants(value) },
            singleLine = true,
            label = { Text("Translation") }
        )

        Text(
            text = "You can write multiple translations separated by \",\" separator.",
            fontSize = TextUnit(12f, TextUnitType.Sp),
            fontWeight = FontWeight.Light
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { viewModel.save() },
        ) {
            Image(imageVector = Icons.Outlined.Save, null, colorFilter = ColorFilter.tint(MaterialTheme.colors.onPrimary))
            Text("Add")
        }
    }
}