package ru.point.sprind.presenter.profile.orders

import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.point.sprind.presenter.general.ConnectionRequiredView
import ru.point.sprind.presenter.general.UsingDefaultAdapterView

interface OrdersViewDefault: UsingDefaultAdapterView, ConnectionRequiredView {
    @StateStrategyType(SkipStrategy::class)
    fun requireAuthorization()
}