package me.y9vad9.vocabulary.screens

interface ScreenNavigator {
    fun gotoWordsList(groupName: String)
    fun gotoGroupCreation()
    fun gotoAddingWord(groupName: String)
    fun editGroupTranslation(groupName: String, id: Long)
    fun goBack()
    fun gotoQuiz(groupNames: List<String>)
}