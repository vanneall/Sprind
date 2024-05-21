package ru.point.sprind.presenter.profile.orders

import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.point.sprind.presenter.general.ConnectionRequiredView
import ru.point.sprind.presenter.general.UsingAdapterView

interface OrdersView: UsingAdapterView, ConnectionRequiredView {
    @StateStrategyType(SkipStrategy::class)
    fun requireAuthorization()
}