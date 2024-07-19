package ru.point.sprind.presenter.cart

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
import ru.point.domain.usecase.interfaces.cart.GetProductsInCartUseCase
import ru.point.domain.usecase.interfaces.cart.MakeOrderUseCase
import ru.point.sprind.adapters.decorators.HorizontalFavoritesItemDecorator
import ru.point.sprind.entity.deletage.product.card.NestedRecyclerViewDelegate
import ru.point.sprind.entity.deletage.product.cart.CartEmptyDelegate
import ru.point.sprind.entity.deletage.product.cart.CartHeaderDelegate
import ru.point.sprind.entity.deletage.product.cart.CartProductDelegate
import ru.point.sprind.entity.deletage.product.cart.CartPromocodeDelegate
import ru.point.sprind.entity.deletage.product.cart.CartSummaryDelegate
import ru.point.sprind.entity.deletage.product.feed.ProductDelegate
import ru.point.sprind.entity.manager.HttpExceptionStatusManager
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@InjectViewState
class CartPresenter @Inject constructor(
    getProductsInCartUseCase: GetProductsInCartUseCase,
    private val makeOrderUseCase: Lazy<MakeOrderUseCase>,
    private val productManager: Lazy<ProductManager>
) : MvpPresenter<CartView>() {

    private val httpExceptionManager = HttpExceptionStatusManager
        .Builder()
        .add403ExceptionHandler { viewState.navigateToAuthorization() }
        .addDefaultExceptionHandler { viewState.showSomethingGoesWrongError() }
        .build()

    val viewDelegates = listOf(
        CartHeaderDelegate(viewState::changeAddress),
        CartProductDelegate(
            onClick = viewState::navigateToProductCard,
            onFavoriteCheckedChange = ::changeProductFavoriteState,
            onDeleteByProductId = ::removeProductFromCart
        ),
        CartPromocodeDelegate(),
        CartSummaryDelegate(),
        CartEmptyDelegate(),
        NestedRecyclerViewDelegate(
            delegates = listOf(
                ProductDelegate(
                    onClickCard = viewState::navigateToProductCard,
                    onBuyClick = { id ->
                        productManager.get().addToCart(
                            productId = id,
                            onComplete = viewState::refresh,
                            onError = ::handleException
                        )
                    },
                    onFavoriteCheckedChange = { id, isFavorite ->
                        productManager.get().changeProductInFavoriteState(
                            productId = id,
                            isInFavorite = isFavorite,
                            onComplete = viewState::refresh,
                            onError = { ex -> handleException(exception = ex) }
                        )
                    }
                )
            ),
            itemDecoration = HorizontalFavoritesItemDecorator()
        )
    )

    private val compositeDisposable = CompositeDisposable()

    init {
        viewState.showLoading(show = true)
        val pagingDisposable = getProductsInCartUseCase.handle()
            .cachedIn(presenterScope)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { data -> viewState.setAdapter(data) },
                { ex -> handleException(exception = ex) }
            )

        compositeDisposable.add(pagingDisposable)
    }

    fun handleException(exception: Throwable) {
        if (exception is HttpException) {
            httpExceptionManager.handle(exception = exception)
        } else {
            viewState.showBadConnection(show = true)
            Log.e("Exception", exception.stackTraceToString())
        }
    }

    private fun removeProductFromCart(id: Long) {
        productManager.get().removeFromCart(
            productId = id,
            onComplete = viewState::refresh,
            onError = ::handleException
        )
    }

    private fun changeProductFavoriteState(
        productId: Long,
        isChecked: Boolean
    ) {
        productManager.get().changeProductInFavoriteState(
            productId = productId,
            isInFavorite = isChecked,
            onComplete = viewState::refresh,
            onError = ::handleException
        )
    }

    fun makeOrder() {
        val makeOrderDisposable = makeOrderUseCase.get().handle()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.navigateToThanksScreen()
                viewState.hidePayButton()
            }, { ex ->
                handleException(exception = ex)
            })

        compositeDisposable.add(makeOrderDisposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
        productManager.get().clearActiveRequests()
    }
}