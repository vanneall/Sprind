package ru.point.sprind.presenter.morda

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.point.domain.entity.ListView
import ru.point.sprind.entity.deletage.Delegate

@StateStrategyType(AddToEndSingleStrategy::class)
interface MordaView : MvpView {
    fun setProductAdapter(list: List<ListView>, delegates: List<Delegate>)
    fun setBadConnection()
    fun setNotFound()
    fun setLoadingScreen()
    fun disableLoadingScreen()
}