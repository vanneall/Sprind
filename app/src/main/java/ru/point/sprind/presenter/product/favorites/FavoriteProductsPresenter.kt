package ru.point.sprind.presenter.product.favorites

import android.util.Log
import androidx.paging.rxjava3.cachedIn
import dagger.Lazy
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import moxy.InjectViewState
import moxy.MvpPresenter
import moxy.presenterScope
import retrofit2.HttpException
import ru.point.domain.manager.ProductManager
import ru.point.domain.usecase.interfaces.favorite.GetFavoritesUseCase
import ru.point.sprind.entity.deletage.product.favorites.EmptyFavoritesDelegate
import ru.point.sprind.entity.deletage.product.feed.ProductDelegate
import ru.point.sprind.entity.manager.HttpExceptionStatusManager
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@InjectViewState
class FavoriteProductsPresenter @Inject constructor(
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val productManager: Lazy<ProductManager>
) : MvpPresenter<FavoriteView>() {

    private val httpExceptionManager = HttpExceptionStatusManager
        .Builder()
        .add403ExceptionHandler { viewState.navigateToAuthorization() }
        .addDefaultExceptionHandler { viewState.showSomethingGoesWrongError() }
        .build()

    private val compositeDisposable = CompositeDisposable()

    val viewDelegates = listOf(
        ProductDelegate(
            onClickCard = viewState::navigateToProductCard,
            onBuyClick = ::addToCart,
            onFavoriteCheckedChange = ::changeFavoriteState
        ),
        EmptyFavoritesDelegate()
    )

    init {
        val disposable = getFavoritesUseCase.handle()
            .cachedIn(presenterScope)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { data -> viewState.setAdapter(views = data) },
                { ex -> handleException(exception = ex) }
            )

        compositeDisposable.add(disposable)
    }


    fun handleException(exception: Throwable) {
        if (exception is HttpException) {
            httpExceptionManager.handle(exception = exception)
        } else {
            viewState.showBadConnection(show = true)
            Log.e("Exception", exception.stackTraceToString())
        }
    }

    private fun addToCart(productId: Long) {
        productManager.get().addToCart(
            productId = productId,
            onError = ::handleException
        )
    }

    private fun changeFavoriteState(
        productId: Long,
        isChecked: Boolean
    ) {
        productManager.get().changeProductInFavoriteState(
            productId = productId,
            isInFavorite = isChecked,
            onComplete = { viewState.refresh() },
            onError = { ex -> handleException(exception = ex) }
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
        productManager.get().clearActiveRequests()
    }
}