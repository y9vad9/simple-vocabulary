package me.y9vad9.vocabulary.screens.groups.items.edit

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import me.y9vad9.vocabulary.screens.Screen
import me.y9vad9.vocabulary.screens.ScreenNavigator
import me.y9vad9.vocabulary.storage.KDSWordsStorage

class EditItemScreen(
    private val groupName: String,
    private val id: Long,
    activity: ComponentActivity,
    private val navigator: ScreenNavigator
) : Screen<EditItemViewModel> {
    override val viewModel: EditItemViewModel by activity.viewModels {
        IntegratedEditItemViewModel.Factory(groupName, id, KDSWordsStorage, navigator)
    }

    @Composable
    override fun render() = EditItemView(viewModel)
}