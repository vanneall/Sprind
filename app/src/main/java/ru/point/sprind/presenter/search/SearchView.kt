package ru.point.sprind.presenter.search

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.point.domain.entity.ListView
import ru.point.sprind.entity.deletage.Delegate

@StateStrategyType(AddToEndSingleStrategy::class)
interface SearchView : MvpView {
    fun setAdapter(delegates: List<Delegate>, view: List<ListView>)

    @StateStrategyType(SkipStrategy::class)
    fun openResult(name: String)
}