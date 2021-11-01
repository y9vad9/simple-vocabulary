package me.y9vad9.vocabulary.entities

interface Selectable<TData> {
    val data: TData
    val isSelected: Boolean
}

fun <T> Selectable<T>.opposite(): Selectable<T> {
    return MutableSelectable(data, !isSelected)
}

data class MutableSelectable<TData>(
    override var data: TData,
    override var isSelected: Boolean
) : Selectable<TData>