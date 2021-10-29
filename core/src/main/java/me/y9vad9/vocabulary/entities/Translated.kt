package me.y9vad9.vocabulary.entities

import kotlinx.serialization.Serializable

@Serializable
data class Translated(
    val id: Long,
    val words: List<String>,
    val variants: List<String>
)