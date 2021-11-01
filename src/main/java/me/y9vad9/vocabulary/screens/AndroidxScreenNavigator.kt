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

    override fun editGroupTranslation(groupName: String, id: Long) {
        navController.navigate("groups/$groupName/$id")
    }

    override fun goBack() {
        navController.navigateUp()
    }

    override fun gotoQuiz(groupNames: List<String>) {
        navController.navigate("quiz/$groupNames")
    }
}