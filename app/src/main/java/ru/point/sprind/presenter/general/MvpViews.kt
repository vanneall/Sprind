package ru.point.sprind.presenter.general

import androidx.paging.PagingData
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.point.domain.entity.view.ViewObject

interface ConnectionRequiredView : MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showBadConnection(show: Boolean = false)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showLoading(show: Boolean = false)

    @StateStrategyType(SkipStrategy::class)
    fun showSomethingGoesWrongError() {}
}

interface UsingDefaultAdapterView: MvpView {
    @StateStrategyType(SkipStrategy::class)
    fun setAdapter(views: List<ViewObject>)
}

interface UsingPagingAdapter: MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setAdapter(views: PagingData<ViewObject>)
}

interface NavigableView: MvpView {
    @StateStrategyType(SkipStrategy::class)
    fun navigateToProductCard(productId: Long) {}

    @StateStrategyType(SkipStrategy::class)
    fun navigateToAuthorization() {}

    @StateStrategyType(SkipStrategy::class)
    fun navigateToReviews() {}

    @StateStrategyType(SkipStrategy::class)
    fun navigateToThanksScreen() {}

    @StateStrategyType(SkipStrategy::class)
    fun navigateToCategoryScreen(categoryId: Long, title: String) {}

    @StateStrategyType(SkipStrategy::class)
    fun navigateToShopScreen(shopId: Long, title: String) {}
}