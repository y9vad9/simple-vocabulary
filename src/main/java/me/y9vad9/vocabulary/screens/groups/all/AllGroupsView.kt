package me.y9vad9.vocabulary.screens.groups.all

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import me.y9vad9.vocabulary.entities.TranslatedGroup
import me.y9vad9.vocabulary.resources.fonts.Manrope

private const val KEY = "Words"

@Composable
fun AllGroupsView(viewModel: AllGroupsViewModel) = Scaffold(
    modifier = Modifier.fillMaxSize(),
    topBar = { Toolbar() },
    floatingActionButton = { FAB(viewModel::onCreateGroupPressed) }
) {
    val isLoading = viewModel.isLoading.collectAsState()
    val words = viewModel.words.collectAsState()

    if (isLoading.value)
        LoadingView()
    else if (words.value.isEmpty())
        NoGroups()
    else GroupsList(
        groups = words.value,
        onItemClicked = viewModel::onTranslatedGroupPressed
    )

    LaunchedEffect(KEY) {
        viewModel.loadWords()
    }
}

@Composable
private fun GroupsList(
    groups: List<TranslatedGroup>,
    onItemClicked: (String) -> Unit
) = LazyColumn(
    modifier = Modifier.fillMaxWidth(),
    contentPadding = PaddingValues(8.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp)
) {
    items(groups) { item ->
        GroupItem(item, onItemClicked)
    }
}

@Composable
@OptIn(ExperimentalUnitApi::class)
private fun GroupItem(
    group: TranslatedGroup,
    onClick: (String) -> Unit
) = Card(modifier = Modifier.fillMaxWidth().selectable(false) { onClick(group.name) }) {
    Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Text(
            text = group.name,
            fontFamily = Manrope,
            fontWeight = FontWeight.Bold,
            fontSize = TextUnit(18f, TextUnitType.Sp)
        )
        Text(
            text = group.translated.map { it.word }.flatten().joinToString(", ").takeIf { it.isNotEmpty() }
                ?: "Nothing in the group",
            fontWeight = FontWeight.Light,
            fontSize = TextUnit(12f, TextUnitType.Sp),
            color = Color.Gray,
            maxLines = 2,
            overflow = TextOverflow.Clip
        )
    }
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
        modifier = Modifier.alpha(0.8f).padding(horizontal = 8.dp),
        text = "You haven't created any groups and words yet!",
        textAlign = TextAlign.Center
    )
}


@Composable
private fun Toolbar() = TopAppBar(
    modifier = Modifier.fillMaxWidth(),
    title = { Text("Groups", fontFamily = Manrope, fontWeight = FontWeight.ExtraBold) }
)

@Composable
private fun FAB(onClick: () -> Unit) = FloatingActionButton(
    onClick = onClick,
    content = { Image(imageVector = Icons.Rounded.Add, null) }
)