package me.y9vad9.vocabulary

import `fun`.kotlingang.kds.KDS
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import me.y9vad9.vocabulary.resources.theme.SimpleVocabularyTheme
import me.y9vad9.vocabulary.screens.NavigationView

class AppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        KDS.onCreate(application)
        setContent {
            SimpleVocabularyTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    NavigationView(rememberNavController(), this)
                }
            }
        }
    }
}