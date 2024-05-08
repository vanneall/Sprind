package ru.point.sprind.presenter.product

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.point.domain.entity.view.ViewObject
import ru.point.sprind.entity.deletage.Delegate

interface ProductCardView : MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setProductAdapter(list: List<ViewObject>, delegates: List<Delegate<*>>)
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showBadConnectionScreen()
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showLoadingScreen()
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun disableLoadingScreen()
    @StateStrategyType(SkipStrategy::class)
    fun openReviews()
}