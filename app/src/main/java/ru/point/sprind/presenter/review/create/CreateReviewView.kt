package ru.point.sprind.presenter.review.create

import moxy.MvpView
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

interface CreateReviewView: MvpView {
    @StateStrategyType(SkipStrategy::class)
    fun exit()
}