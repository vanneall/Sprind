package ru.point.sprind.presenter.auth.registration.password

import moxy.MvpView
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

interface RegistrationView: MvpView {
    @StateStrategyType(SkipStrategy::class)
    fun showError()
    @StateStrategyType(SkipStrategy::class)
    fun exitFromRegistration()
}
