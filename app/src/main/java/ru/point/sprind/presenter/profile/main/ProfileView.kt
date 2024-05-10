package ru.point.sprind.presenter.profile.main

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface ProfileView : MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setUsername(name: String)
}