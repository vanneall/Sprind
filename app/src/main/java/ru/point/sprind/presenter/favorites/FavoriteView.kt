package ru.point.sprind.presenter.favorites

import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.point.sprind.presenter.general.ConnectionRequiredView
import ru.point.sprind.presenter.general.UsingPagingAdapter

interface FavoriteView : ConnectionRequiredView, UsingPagingAdapter {
    @StateStrategyType(SkipStrategy::class)
    fun openCard(id: Long)
    @StateStrategyType(SkipStrategy::class)
    fun requireAuthorization()
}