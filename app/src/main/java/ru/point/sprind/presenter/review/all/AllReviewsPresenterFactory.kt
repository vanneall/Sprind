package ru.point.sprind.presenter.review.all

import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory

@AssistedFactory
interface AllReviewsPresenterFactory {

    fun create(@Assisted(ID) id: Long): AllReviewsPresenter

    companion object {
        const val ID = "ID"
    }
}