package ru.point.sprind.presenter.review.all

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.point.domain.entity.view.ViewObject

interface AllReviewsView: MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setProductAdapter(views: List<ViewObject>)
}