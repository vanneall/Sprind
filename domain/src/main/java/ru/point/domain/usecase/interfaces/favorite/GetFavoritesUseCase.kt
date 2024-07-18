package ru.point.domain.usecase.interfaces.favorite

import androidx.paging.PagingData
import io.reactivex.rxjava3.core.Observable
import ru.point.domain.entity.view.ViewObject

interface GetFavoritesUseCase {
    fun handle(): Observable<PagingData<ViewObject>>
}