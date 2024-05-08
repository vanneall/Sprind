package ru.point.sprind.presenter.favorites

import android.util.Log
import dagger.Lazy
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.point.domain.usecase.interfaces.cart.AddProductToCartUseCase
import ru.point.domain.usecase.interfaces.favorite.ChangeFavoriteStateUseCase
import ru.point.domain.usecase.interfaces.favorite.GetFavoritesUseCase
import ru.point.sprind.entity.deletage.product.feed.ProductDelegate
import javax.inject.Inject

@InjectViewState
class FavoritePresenter @Inject constructor(
    private val addProductToCartUseCase: Lazy<AddProductToCartUseCase>,
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val changeFavoriteStateUseCase: Lazy<ChangeFavoriteStateUseCase>,
) : MvpPresenter<FavoriteView>() {

    private val compositeDisposable = CompositeDisposable()

    val delegates = listOf(
        ProductDelegate(
            onClickCard = viewState::openCard,
            onBuyClick = addProductToCartUseCase.get()::handle,
            onFavoriteCheckedChange = changeFavoriteStateUseCase.get()::handle
        )
    )

    fun getFavorites() {
        val disposable = getFavoritesUseCase.handle()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                viewState.setAdapter(
                    view = list
                )
            }, { ex ->
                Log.e("Favorite page", ex.stackTraceToString())
            })
        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

}