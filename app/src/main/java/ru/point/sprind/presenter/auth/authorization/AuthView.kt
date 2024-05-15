package ru.point.sprind.presenter.auth.authorization

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

interface AuthView : MvpView {
    @StateStrategyType(SkipStrategy::class)
    fun successfulAuthorization()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun displayErrorOnInputLayout()
}