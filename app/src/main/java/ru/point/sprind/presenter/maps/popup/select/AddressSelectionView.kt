package ru.point.sprind.presenter.maps.popup.select

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

interface AddressSelectionView : MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun closeAddressSelectionStack()
    @StateStrategyType(SkipStrategy::class)
    fun displaySomethingGoesWrongError()
}