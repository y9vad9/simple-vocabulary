package me.y9vad9.vocabulary.screens

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import me.y9vad9.vocabulary.screens.groups.create.CreateGroupScreen
import me.y9vad9.vocabulary.screens.groups.get.AllGroupsScreen
import me.y9vad9.vocabulary.screens.groups.item.add.AddTranslationScreen
import me.y9vad9.vocabulary.screens.groups.item.get.GetGroupItemsScreen

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
            GetGroupItemsScreen(activity, it.arguments?.getString("groupName")!!, navigator).render()
        }
    }
}