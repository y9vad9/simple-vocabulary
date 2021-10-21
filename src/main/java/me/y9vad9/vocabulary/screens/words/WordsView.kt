package me.y9vad9.vocabulary.screens.words

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import me.y9vad9.vocabulary.entities.Translated
import me.y9vad9.vocabulary.entities.TranslatedGroup

private const val KEY = "Words"

@Composable
fun WordsView(viewModel: WordsViewModel) = Scaffold(
    modifier = Modifier.fillMaxSize(),
    topBar = ::Toolbar,
    floatingActionButton = { FAB(viewModel::onCreateGroupPressed) }
) {
    val isLoading = viewModel.isLoading.collectAsState()
    val words = viewModel.words.collectAsState()

    if (isLoading.value)
        LoadingView()
    else if (words.value.isEmpty())
        NoGroups()
    else WordsList(words.value, viewModel::onTranslatedItemPressed)

    LaunchedEffect(KEY) {
        viewModel.loadWords()
    }
}

@Composable
private fun WordsList(groups: List<TranslatedGroup>, onItemClicked: (Translated) -> Unit) = LazyColumn(
    modifier = Modifier.fillMaxWidth(),
    contentPadding = PaddingValues(8.dp)
) {
    items(groups) { item ->
        GroupItem(item)
    }
}

@Composable
@OptIn(ExperimentalUnitApi::class)
private fun GroupItem(group: TranslatedGroup) = Card(modifier = Modifier.fillMaxWidth()) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Box(modifier = Modifier.fillMaxWidth().background(Color.Gray)) {
            Text(group.name, fontSize = TextUnit(18f, TextUnitType.Sp))
        }

        if (group.translated.isEmpty())
            Text(modifier = Modifier.align(Alignment.CenterHorizontally), text = "Nothing here")
        else TranslatedList(group.translated)
    }
}

@Composable
private fun TranslatedList(list: List<Translated>) = LazyColumn(modifier = Modifier.fillMaxWidth()) {
    items(list) { item -> TranslatedItem(item) }
}

private fun TranslatedItem(translated: Translated) = Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
    Text(
        modifier = Modifier.weight(1f),
        text = translated.word.joinToString(",")
    )
    Text(
        modifier = Modifier.weight(1f),
        text = translated.variants.joinToString(",")
    )
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
private fun NoGroups() = Column(
    Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
) {
    Text(
        modifier = Modifier.alpha(0.8f),
        text = "You haven't created any groups and words yet!"
    )
}


@Composable
private fun Toolbar() = TopAppBar(
    modifier = Modifier.fillMaxWidth(),
    title = { Text("Vocabulary") }
)

@Composable
private fun FAB(onClick: () -> Unit) = FloatingActionButton(
    modifier = Modifier.fillMaxWidth(),
    onClick = onClick,
    content = { Image(imageVector = Icons.Rounded.Add, null) }
)