package me.y9vad9.vocabulary.entities

import kotlinx.serialization.Serializable

@Serializable
data class TranslatedGroup(
    val name: String,
    val translated: List<Translated>
)