package ru.point.sprind.presenter.review.create

import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory

@AssistedFactory
interface CreateReviewPresenterFactory {
    fun create(@Assisted(ID) id: Long): CreateReviewPresenter

    companion object {
        const val ID = "ID"
    }
}