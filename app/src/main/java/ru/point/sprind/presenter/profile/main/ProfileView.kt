package ru.point.sprind.presenter.profile.main

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.point.sprind.presenter.general.NavigableView

interface ProfileView : MvpView, NavigableView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setUsername(name: String?)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setIsDarkThemeEnabled(isEnabled: Boolean)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setLogIn()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setLogOut()
}