package ru.point.sprind.presenter.morda

import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.point.domain.entity.ListView
import ru.point.sprind.entity.deletage.Delegate

interface MordaView : MvpView {
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun setProductAdapter(list: List<ListView>, delegates: List<Delegate>)
}