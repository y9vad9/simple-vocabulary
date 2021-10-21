package me.y9vad9.vocabulary.screens

interface ScreenNavigator {
    fun gotoWordsList()
    fun gotoGroupCreation()
    fun gotoAddingWord(groupName: String)
}