package me.y9vad9.vocabulary.entities

@JvmInline
value class Language(val code: String) {
    companion object {
        val UK: Language = Language("uk")
        val RU: Language = Language("ru")
        val EN: Language = Language("en")
    }
}