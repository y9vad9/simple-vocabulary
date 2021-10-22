package me.y9vad9.vocabulary.screens.groups.all

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import me.y9vad9.vocabulary.screens.AndroidxScreenNavigator
import me.y9vad9.vocabulary.screens.Screen
import me.y9vad9.vocabulary.storage.KDSWordsStorage

class AllGroupsScreen(activity: ComponentActivity, navController: NavController) : Screen<AllGroupsViewModel> {
    override val viewModel: AllGroupsViewModel by activity.viewModels {
        IntegratedAllGroupsViewModel.Factory(
            KDSWordsStorage,
            AndroidxScreenNavigator(navController)
        )
    }

    @Composable
    override fun render() = AllGroupsView(viewModel)
}