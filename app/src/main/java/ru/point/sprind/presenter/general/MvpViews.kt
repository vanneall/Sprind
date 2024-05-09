package ru.point.sprind.presenter.general

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.point.domain.entity.view.ViewObject

interface ConnectionRequiredView : MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun displayBadConnectionScreen(show: Boolean = false)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun displayLoadingScreen(show: Boolean = false)

    @StateStrategyType(SkipStrategy::class)
    fun displaySomethingGoesWrongError() {}
}

interface UsingAdapterView: MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setAdapter(views: List<ViewObject>)
}