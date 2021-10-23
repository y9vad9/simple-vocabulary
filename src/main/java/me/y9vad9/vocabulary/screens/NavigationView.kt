package me.y9vad9.vocabulary.screens

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import me.y9vad9.vocabulary.screens.groups.add_translation.AddTranslationScreen
import me.y9vad9.vocabulary.screens.groups.all.AllGroupsScreen
import me.y9vad9.vocabulary.screens.groups.create.CreateGroupScreen
import me.y9vad9.vocabulary.screens.groups.view.ViewTranslationScreen

@Composable
fun NavigationView(navController: NavHostController, activity: ComponentActivity) {
    val navigator: ScreenNavigator = AndroidxScreenNavigator(navController)
    NavHost(navController = navController, startDestination = "groups/all") {
        val navigator = AndroidxScreenNavigator(navController)
        composable("groups/all") {
            AllGroupsScreen(activity, navigator).render()
        }
        composable("groups/create") {
            CreateGroupScreen(activity, navigator).render()
        }
        composable(
            "groups/{groupName}/add"
        ) {
            AddTranslationScreen(it.arguments?.getString("groupName")!!, activity, navigator).render()
        }
        composable("groups/{groupName}/view") {
            ViewTranslationScreen(activity, it.arguments?.getString("groupName")!!, navigator).render()
        }
    }
}