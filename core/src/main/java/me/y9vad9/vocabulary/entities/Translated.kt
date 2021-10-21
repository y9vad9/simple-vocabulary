package me.y9vad9.vocabulary.entities

data class Translated(
    val id: Long,
    val word: List<Word>,
    val variants: List<Word>
)