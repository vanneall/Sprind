package ru.point.domain.usecase.interfaces.favorite

import io.reactivex.rxjava3.core.Completable

interface ChangeFavoriteStateUseCase {
    fun handle(id: Long, isFavorite: Boolean): Completable
}