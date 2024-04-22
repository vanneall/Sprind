package ru.point.sprind.presenter.morda

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.point.domain.entity.ListView
import ru.point.sprind.entity.deletage.Delegate


interface MordaView : MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setProductAdapter(list: List<ListView>, delegates: List<Delegate>)
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setBadConnection()
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setNotFound()
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setLoadingScreen()
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun disableLoadingScreen()
    @StateStrategyType(SkipStrategy::class)
    fun openCard(id: Long)
}