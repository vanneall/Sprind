package ru.point.domain.usecase.interfaces.favorite

interface ChangeFavoriteStateUseCase {
    fun handle(id: Long, isFavorite: Boolean)
}