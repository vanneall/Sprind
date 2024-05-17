package ru.point.sprind.presenter.search

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.point.domain.entity.view.ViewObject

interface SearchRequestView : MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setAdapter(list: List<ViewObject>)
}