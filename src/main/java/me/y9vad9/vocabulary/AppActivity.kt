package me.y9vad9.vocabulary

import `fun`.kotlingang.kds.KDS
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class AppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        KDS.onCreate(application)
        setContent {

        }
    }
}