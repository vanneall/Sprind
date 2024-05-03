package ru.point.domain.usecase.interfaces

interface ChangeFavoriteStateUseCase {
    fun handle(id: Long, isFavorite: Boolean)
}