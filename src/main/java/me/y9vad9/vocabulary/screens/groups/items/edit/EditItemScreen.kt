package me.y9vad9.vocabulary.screens.groups.items.edit

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import me.y9vad9.vocabulary.screens.Screen
import me.y9vad9.vocabulary.screens.ScreenNavigator
import me.y9vad9.vocabulary.storage.KDSWordsStorage

class EditItemScreen(
    private val groupName: String,
    private val id: Long,
    private val navigator: ScreenNavigator
) : Screen<EditItemViewModel> {
    override val viewModel: EditItemViewModel
        @Composable get() = viewModel(
            factory = IntegratedEditItemViewModel.Factory(groupName, id, KDSWordsStorage, navigator)
        )

    @Composable
    override fun render() = EditItemView(viewModel)
}