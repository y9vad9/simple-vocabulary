package me.y9vad9.vocabulary.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import me.y9vad9.vocabulary.screens.groups.create.CreateGroupScreen
import me.y9vad9.vocabulary.screens.groups.get.AllGroupsScreen
import me.y9vad9.vocabulary.screens.groups.items.add.AddTranslationScreen
import me.y9vad9.vocabulary.screens.groups.items.edit.EditItemScreen
import me.y9vad9.vocabulary.screens.groups.items.get.GetGroupItemsScreen
import me.y9vad9.vocabulary.screens.quiz.QuizScreen

@Composable
fun NavigationView(navController: NavHostController) {
    val navigator: ScreenNavigator = AndroidxScreenNavigator(navController)
    NavHost(navController = navController, startDestination = "groups/all") {
        composable("groups/all") {
            AllGroupsScreen(navigator).render()
        }
        composable("groups/create") {
            CreateGroupScreen(navigator).render()
        }
        composable(
            "groups/{groupName}/add"
        ) {
            AddTranslationScreen(it.arguments?.getString("groupName")!!, navigator).render()
        }
        composable("groups/{groupName}/view") {
            GetGroupItemsScreen(it.arguments?.getString("groupName")!!, navigator).render()
        }
        composable(
            route = "groups/{groupName}/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.LongType
            })
        ) {
            EditItemScreen(
                it.arguments?.getString("groupName")!!,
                it.arguments?.getLong("id")!!, navigator
            ).render()
        }
        composable(
            route = "quiz/{groupNames}",
            arguments = listOf(navArgument("groupNames") { type = NavType.StringType })
        ) {
            QuizScreen(it.arguments!!.getString("groupNames")!!.split(","), navigator).render()
        }
    }
}