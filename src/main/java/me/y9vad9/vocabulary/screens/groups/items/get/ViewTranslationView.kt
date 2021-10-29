package me.y9vad9.vocabulary.screens.groups.items.get

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import me.y9vad9.vocabulary.entities.Translated
import me.y9vad9.vocabulary.widgets.VocabularyToolbar

private const val KEY = "TranslationGroupView"

@Composable
fun ViewTranslationView(viewModel: GetGroupViewModel) = Scaffold(
    modifier = Modifier.fillMaxSize(),
    topBar = {
        VocabularyToolbar("Words", onBackPressed = { viewModel.onBackPressed() }) {
            if (viewModel.isPlayButtonEnabled.value) {
                IconButton(
                    onClick = { viewModel.onPlayButtonClicked() },
                ) {
                    Icon(imageVector = Icons.Rounded.PlayArrow, "Start quiz", tint = Color.White)
                }
            }
        }
    },
    floatingActionButton = { FAB(onClick = { viewModel.onAddButtonClicked() }) }
) {
    val isLoading = viewModel.isLoading.collectAsState()
    val words = viewModel.words.collectAsState()
    if (isLoading.value)
        LoadingView()
    else if (words.value.isEmpty())
        NoItems()
    else TranslatedList(words.value, onItemSelected = { viewModel.onTranslatedClicked(it.id) })

    LaunchedEffect(KEY) {
        viewModel.loadWords()
    }
}

@Composable
private fun TranslatedList(translatedList: List<Translated>, onItemSelected: (Translated) -> Unit) = LazyColumn(modifier = Modifier.fillMaxSize()) {
    items(translatedList) { item -> TranslatedItem(item, onItemSelected) }
}

@Composable
private fun LoadingView() = Column(
    Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
) {
    CircularProgressIndicator(Modifier.size(50.dp), color = MaterialTheme.colors.secondary)
}

@Composable
private fun NoItems() = Column(
    Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
) {
    Text(
        modifier = Modifier.align(Alignment.CenterHorizontally),
        text = "You haven't added any words in this group.",
        color = Color.Gray,
        textAlign = TextAlign.Center
    )
}


@Composable
private fun TranslatedItem(translated: Translated, onItemSelected: (Translated) -> Unit) = Row(
    modifier = Modifier.fillMaxWidth().selectable(false) {
        onItemSelected(translated)
    }) {
    Card(
        modifier = Modifier.weight(1f),
        border = BorderStroke(1.dp, MaterialTheme.colors.onBackground),
        shape = RoundedCornerShape(topStart = 0.dp),
        backgroundColor = MaterialTheme.colors.background
    ) {
        Text(
            modifier = Modifier.weight(1f).padding(16.dp).fillMaxWidth(),
            text = translated.words.joinToString(","),
            textAlign = TextAlign.Center
        )
    }

    Card(
        modifier = Modifier.weight(1f),
        border = BorderStroke(1.dp, MaterialTheme.colors.onBackground),
        shape = RoundedCornerShape(topStart = 0.dp),
        backgroundColor = MaterialTheme.colors.background
    ) {
        Text(
            modifier = Modifier.weight(1f).padding(16.dp).fillMaxWidth(),
            text = translated.variants.joinToString(","),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun FAB(onClick: () -> Unit) = FloatingActionButton(
    onClick = onClick,
    content = { Image(imageVector = Icons.Rounded.Add, null) }
)