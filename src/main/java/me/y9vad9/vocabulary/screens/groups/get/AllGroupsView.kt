package me.y9vad9.vocabulary.screens.groups.get

import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.DeleteForever
import androidx.compose.material.icons.rounded.DoneAll
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import me.y9vad9.vocabulary.entities.Selectable
import me.y9vad9.vocabulary.entities.TranslatedGroup
import me.y9vad9.vocabulary.resources.fonts.Manrope

private const val KEY = "Words"

@Composable
fun AllGroupsView(viewModel: AllGroupsViewModel) {
    val isLoading = viewModel.isLoading.collectAsState()
    val groups = viewModel.groups.collectAsState()
    val isAnyItemSelected = viewModel.isAnyItemSelected.collectAsState()
    val isCanPlay = viewModel.isCanPlay.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Toolbar(
                isAnyItemSelected.value,
                isCanPlay.value,
                onDeleteClicked = { viewModel.onDeleteGroupsClicked() },
                onPlayClicked = { viewModel.onPlayButtonPressed() }
            )
        },
        floatingActionButton = { FAB(viewModel::onCreateGroupPressed) }
    ) {
        if (isLoading.value)
            LoadingView()
        else if (groups.value.isEmpty())
            NoGroups()
        else GroupsList(
            groups = groups.value,
            onItemClicked = viewModel::onTranslatedGroupPressed,
            onItemSelected = { viewModel.selectItem(it.name) }
        )

        LaunchedEffect(KEY) {
            viewModel.loadWords()
        }
    }
}

@Composable
private fun GroupsList(
    groups: List<Selectable<TranslatedGroup>>,
    onItemClicked: (String) -> Unit,
    onItemSelected: (TranslatedGroup) -> Unit
) = LazyColumn(
    modifier = Modifier.fillMaxWidth(),
    contentPadding = PaddingValues(8.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp)
) {
    items(groups) { item ->
        GroupItem(item, onItemClicked, onItemSelected)
    }
}

@Composable
@OptIn(ExperimentalUnitApi::class, androidx.compose.foundation.ExperimentalFoundationApi::class)
private fun GroupItem(
    group: Selectable<TranslatedGroup>,
    onClick: (String) -> Unit,
    onSelected: (TranslatedGroup) -> Unit
) = Card(
    modifier = Modifier.fillMaxWidth().combinedClickable(
        onClick = { onClick(group.data.name) },
        onLongClick = { onSelected(group.data) }
    )
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (group.isSelected) {
            Box(modifier = Modifier.padding(8.dp)) {
                Image(
                    modifier = Modifier.size(20.dp),
                    imageVector = Icons.Rounded.DoneAll,
                    contentDescription = "Selected",
                    colorFilter = ColorFilter.tint(Color.Green.copy(alpha = 0.4f))
                )
            }
        }
        Column(modifier = Modifier.fillMaxWidth().padding(start = 8.dp)) {
            Text(
                text = group.data.name,
                fontFamily = Manrope,
                fontWeight = FontWeight.Bold,
                fontSize = TextUnit(18f, TextUnitType.Sp)
            )
            Text(
                text = group.data.translated.map { it.words }.flatten().joinToString(", ")
                    .takeIf { it.isNotEmpty() }
                    ?: "Nothing in the group",
                fontWeight = FontWeight.Light,
                fontSize = TextUnit(12f, TextUnitType.Sp),
                color = Color.Gray,
                maxLines = 2,
                overflow = TextOverflow.Clip
            )
        }
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
private fun Toolbar(
    isAnyItemSelected: Boolean,
    isCanPlay: Boolean,
    onDeleteClicked: () -> Unit,
    onPlayClicked: () -> Unit
) = TopAppBar(
    modifier = Modifier.fillMaxWidth(),
    title = { Text("Groups", fontFamily = Manrope, fontWeight = FontWeight.ExtraBold) },
    actions = {
        if (isAnyItemSelected) {
            if (isCanPlay) {
                IconButton(
                    onClick = onPlayClicked
                ) {
                    Icon(imageVector = Icons.Rounded.PlayArrow, "Start quiz")
                }
            }
            IconButton(
                onClick = onDeleteClicked
            ) {
                Icon(imageVector = Icons.Rounded.DeleteForever, "Start quiz")
            }
        }
    }
)

@Composable
private fun FAB(onClick: () -> Unit) = FloatingActionButton(
    onClick = onClick,
    content = { Image(imageVector = Icons.Rounded.Add, null) }
)