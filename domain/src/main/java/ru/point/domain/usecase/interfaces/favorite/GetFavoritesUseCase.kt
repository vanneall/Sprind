package ru.point.domain.usecase.interfaces.favorite

import androidx.paging.rxjava3.RxPagingSource
import ru.point.domain.entity.view.ViewObject

interface GetFavoritesUseCase {
    fun handle(): RxPagingSource<Int, ViewObject>
}