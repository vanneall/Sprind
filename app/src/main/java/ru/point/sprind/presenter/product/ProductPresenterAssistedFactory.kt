package ru.point.sprind.presenter.product

import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory

@AssistedFactory
interface ProductPresenterAssistedFactory {
    fun create(@Assisted(ID) productId: Long): ProductPresenter

    companion object {
        const val ID = "ID"
    }
}