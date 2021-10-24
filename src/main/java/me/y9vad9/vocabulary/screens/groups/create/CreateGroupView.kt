package me.y9vad9.vocabulary.screens.groups.create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.y9vad9.vocabulary.widgets.VocabularyToolbar

@Composable
fun CreateGroupView(viewModel: CreateGroupViewModel) = Scaffold(
    modifier = Modifier.fillMaxSize(),
    topBar = { VocabularyToolbar("New group", onBackPressed = { viewModel.onBackPressed() }) }
) {
    val name = viewModel.name.collectAsState()
    val isLoading = viewModel.isLoading.collectAsState()
    Column(modifier = Modifier.padding(8.dp)) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            value = name.value,
            onValueChange = { value -> viewModel.processName(value) },
            singleLine = true,
            label = { Text("Group name") }
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { viewModel.onCreateButtonClicked() },
        ) {
            if (isLoading.value)
                CircularProgressIndicator()
            else Text("Save")
        }
    }
}