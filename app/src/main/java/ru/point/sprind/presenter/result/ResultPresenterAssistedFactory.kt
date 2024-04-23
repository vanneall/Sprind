package ru.point.sprind.presenter.result

import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory

@AssistedFactory
interface ResultPresenterAssistedFactory {
    fun create(@Assisted(QUERY) request: String): ResultPresenter

    companion object {
        const val QUERY = "QUERY"
    }
}