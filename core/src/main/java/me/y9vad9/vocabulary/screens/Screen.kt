package me.y9vad9.vocabulary.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel

interface Screen<VM : ViewModel> {
    val viewModel: VM
        @Composable get

    @Composable
    fun render()
}