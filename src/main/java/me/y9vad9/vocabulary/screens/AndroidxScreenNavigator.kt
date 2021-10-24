package me.y9vad9.vocabulary.screens

import androidx.navigation.NavController

class AndroidxScreenNavigator(private val navController: NavController) : ScreenNavigator {
    override fun gotoWordsList(groupName: String) {
        navController.navigate("groups/$groupName/view") {
            restoreState = false
        }
    }

    override fun gotoGroupCreation() {
        navController.navigate("groups/create")
    }

    override fun gotoAddingWord(groupName: String) {
        navController.navigate("groups/$groupName/add")
    }

    override fun goBack() {
        navController.navigateUp()
    }
}