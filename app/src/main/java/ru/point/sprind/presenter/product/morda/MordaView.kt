package ru.point.sprind.presenter.product.morda

import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.point.sprind.presenter.general.ConnectionRequiredView
import ru.point.sprind.presenter.general.UsingAdapterView


interface MordaView : ConnectionRequiredView, UsingAdapterView {
    @StateStrategyType(SkipStrategy::class)
    fun openCard(id: Long)
    @StateStrategyType(SkipStrategy::class)
    fun requireAuthorization()
}