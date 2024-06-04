package ru.point.sprind.presenter.favorites

import dagger.Lazy
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter
import retrofit2.HttpException
import ru.point.domain.usecase.interfaces.cart.AddProductToCartUseCase
import ru.point.domain.usecase.interfaces.favorite.ChangeFavoriteStateUseCase
import ru.point.domain.usecase.interfaces.favorite.GetFavoritesUseCase
import ru.point.sprind.entity.deletage.product.favorites.EmptyFavoritesDelegate
import ru.point.sprind.entity.deletage.product.feed.ProductDelegate
import ru.point.sprind.entity.manager.HttpExceptionStatusManager
import javax.inject.Inject

@InjectViewState
class FavoritePresenter @Inject constructor(
    private val addProductToCartUseCase: Lazy<AddProductToCartUseCase>,
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val changeFavoriteStateUseCase: Lazy<ChangeFavoriteStateUseCase>,
) : MvpPresenter<FavoriteView>() {

    private val httpManager = HttpExceptionStatusManager
        .Builder()
        .add403ExceptionHandler { viewState::requireAuthorization }
        .addDefaultExceptionHandler { viewState::displaySomethingGoesWrongError }
        .build()

    private val compositeDisposable = CompositeDisposable()

    val delegates = listOf(
        ProductDelegate(
            onClickCard = viewState::openCard,
            onBuyClick = ::onAddProductToCart,
            onFavoriteCheckedChange = ::onCheckedFavoriteStateChange
        ),
        EmptyFavoritesDelegate()
    )

    fun getFavorites() {
        val disposable = getFavoritesUseCase.handle()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                viewState.displayLoadingScreen(show = false)
                viewState.setAdapter(list)
            }, { ex ->
                if (ex is HttpException) httpManager.handle(ex)
                else viewState.displayBadConnectionScreen(show = true)
            })
        compositeDisposable.add(disposable)
    }

    private fun onAddProductToCart(productId: Long) {
        val disposable = addProductToCartUseCase.get().handle(id = productId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({}, { ex ->
                if (ex is HttpException) httpManager.handle(ex)
            })

        compositeDisposable.add(disposable);
    }

    private fun onCheckedFavoriteStateChange(
        productId: Long,
        isChecked: Boolean,
        isSuccessfulCallback: (Boolean) -> Unit,
    ) {
        val disposable = changeFavoriteStateUseCase.get()
            .handle(id = productId, isFavorite = isChecked)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                isSuccessfulCallback(true)
            }, { ex ->
                isSuccessfulCallback(false)
                if (ex is HttpException) httpManager.handle(ex)
            })

        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}