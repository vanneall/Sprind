package ru.point.sprind.presenter.product.card

import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.point.sprind.presenter.general.ConnectionRequiredView
import ru.point.sprind.presenter.general.UsingAdapterView

interface ProductCardView : ConnectionRequiredView, UsingAdapterView {
    @StateStrategyType(SkipStrategy::class)
    fun openReviews()
    @StateStrategyType(SkipStrategy::class)
    fun requireAuthorization()
}