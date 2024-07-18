package ru.point.domain.usecase.implementation.favorite

import ru.point.domain.paging.FavoritePagingSource
import ru.point.domain.usecase.interfaces.favorite.GetFavoritesUseCase

class GetFavoritesUseCaseImpl(
    private val pagingSource: FavoritePagingSource
) : GetFavoritesUseCase {
    override fun handle() = pagingSource
}