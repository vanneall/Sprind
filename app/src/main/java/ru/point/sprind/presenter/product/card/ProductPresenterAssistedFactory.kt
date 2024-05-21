package ru.point.sprind.presenter.product.card

import androidx.lifecycle.Lifecycle
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory

@AssistedFactory
interface ProductPresenterAssistedFactory {
    fun create(
        @Assisted(ID) productId: Long,
        @Assisted(LIFECYCLE) lifecycle: Lifecycle
    ): ProductPresenter

    companion object {
        const val ID = "ID"
        const val LIFECYCLE = "LIFECYCLE"
    }
}