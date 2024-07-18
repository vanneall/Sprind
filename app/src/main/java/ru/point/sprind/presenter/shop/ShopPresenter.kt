package ru.point.sprind.presenter.shop

import android.util.Log
import androidx.paging.Pager
import androidx.paging.rxjava3.cachedIn
import androidx.paging.rxjava3.observable
import dagger.Lazy
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter
import moxy.presenterScope
import retrofit2.HttpException
import ru.point.domain.manager.ProductManager
import ru.point.domain.usecase.interfaces.shop.GetShopProductsUseCase
import ru.point.sprind.entity.deletage.product.feed.ProductDelegate
import ru.point.sprind.entity.manager.HttpExceptionStatusManager
import ru.point.sprind.presenter.category.CategoryPresenter.Factory.Companion.ID
import ru.point.sprind.utils.pagerConfig

@InjectViewState
class ShopPresenter @AssistedInject constructor(
    @Assisted(ID) shopId: Long,
    getShopProductsUseCase: GetShopProductsUseCase,
    private val productManager: Lazy<ProductManager>
) : MvpPresenter<ShopView>() {

    private val httpExceptionManager = HttpExceptionStatusManager
        .Builder()
        .add403ExceptionHandler { viewState.navigateToAuthorization() }
        .addDefaultExceptionHandler { viewState.showSomethingGoesWrongError() }
        .build()

    private val mainCompositeDisposable = CompositeDisposable()

    val viewDelegates = listOf(
        ProductDelegate(
            onClickCard = viewState::navigateToProductCard,
            onBuyClick = ::addProductToCart,
            onFavoriteCheckedChange = ::changeProductInFavoriteState
        )
    )

    init {
        val mainDisposable = Pager(
            config = pagerConfig,
            pagingSourceFactory = { getShopProductsUseCase.handle(shopId = shopId) }
        ).observable
            .cachedIn(presenterScope)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { pagingData -> viewState.setAdapter(pagingData) },
                { ex -> handleException(exception = ex) }
            )

        mainCompositeDisposable.add(mainDisposable)
    }

    private fun handleException(exception: Throwable) {
        if (exception is HttpException) {
            httpExceptionManager.handle(exception = exception)
        } else {
            viewState.showBadConnection(show = true)
            Log.e("Exception", exception.stackTraceToString())
        }
    }

    private fun addProductToCart(productId: Long) {
        productManager.get().addToCart(
            productId = productId,
            onError = ::handleException
        )
    }

    private fun changeProductInFavoriteState(
        productId: Long,
        isInFavoriteNow: Boolean,
        changeFavoriteStateCallback: (isSuccessful: Boolean) -> Unit,
    ) {
        productManager.get().changeProductInFavoriteState(
            productId = productId,
            isInFavorite = isInFavoriteNow,
            onComplete = { changeFavoriteStateCallback(true) },
            onError = { ex ->
                changeFavoriteStateCallback(false)
                handleException(exception = ex)
            }
        )
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted(ID) shopId: Long): ShopPresenter

        companion object {
            const val ID = "ru.point.sprind.presenter.category.ID"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mainCompositeDisposable.clear()
        productManager.get().clearActiveRequests()
    }
}