package me.y9vad9.vocabulary.screens.quiz

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import me.y9vad9.vocabulary.entities.VariantType
import me.y9vad9.vocabulary.widgets.VocabularyToolbar

private const val KEY = "Quiz"

@Composable
fun QuizView(viewModel: QuizViewModel) = Scaffold(
    modifier = Modifier.fillMaxSize(),
    topBar = { VocabularyToolbar("Quiz", onBackPressed = { viewModel.onBackPressed() }) }
) {
    val word = viewModel.word.collectAsState()
    val variants = viewModel.variants.collectAsState()
    val currentQuizState = viewModel.currentQuizState.collectAsState()
    val isLoading = viewModel.isLoading.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize().padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isLoading.value)
            CircularProgressIndicator(Modifier.size(50.dp), color = MaterialTheme.colors.secondary)
        else if (currentQuizState.value == VariantType.UNSPECIFIED)
            QuizContent(word.value, variants.value, viewModel::onVariantChosen)
        else VariantChosenResult(currentQuizState.value == VariantType.CORRECT)
    }
    LaunchedEffect(KEY) {
        viewModel.loadWords()
    }
}

@Composable
private fun QuizContent(word: String, variants: List<Pair<String, VariantType>>, onVariantChosen: (String) -> Unit) {
    Text("Translate of '$word' is", fontWeight = FontWeight.Medium)
    LazyColumn {
        items(variants) { item ->
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onVariantChosen(item.first) }
            ) {
                if (item.second == VariantType.INCORRECT)
                    Icon(imageVector = Icons.Rounded.Close, null)
                else if (item.second == VariantType.CORRECT)
                    Icon(imageVector = Icons.Rounded.Check, null)
                Text(
                    text = item.first
                )
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun VariantChosenResult(success: Boolean) {
    Image(
        modifier = Modifier.width((LocalConfiguration.current.screenWidthDp / 2).dp).background(
            if (success) Color.Green else Color.Red, RoundedCornerShape(50)
        ),
        imageVector = if (success) Icons.Rounded.Check else Icons.Rounded.Close,
        contentDescription = if (success) "Success" else "Fail",
        colorFilter = ColorFilter.tint(MaterialTheme.colors.background),
        contentScale = ContentScale.FillWidth
    )
}