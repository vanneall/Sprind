package ru.point.sprind.presenter.cart

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import androidx.paging.rxjava3.observable
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
import ru.point.sprind.entity.deletage.product.cart.CartEmptyDelegate
import ru.point.sprind.entity.deletage.product.cart.CartHeaderDelegate
import ru.point.sprind.entity.deletage.product.cart.CartProductDelegate
import ru.point.sprind.entity.deletage.product.cart.CartPromocodeDelegate
import ru.point.sprind.entity.deletage.product.cart.CartSummaryDelegate
import ru.point.sprind.entity.manager.HttpExceptionStatusManager
import ru.point.sprind.utils.pagerConfig
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@InjectViewState
class CartPresenter @Inject constructor(
    private val getProductsInCartUseCase: GetProductsInCartUseCase,
    private val makeOrderUseCase: Lazy<MakeOrderUseCase>,
    private val productManager: Lazy<ProductManager>
) : MvpPresenter<CartView>() {

    private val httpExceptionManager = HttpExceptionStatusManager
        .Builder()
        .add403ExceptionHandler { viewState.navigateToAuthorization() }
        .addDefaultExceptionHandler { viewState.showSomethingGoesWrongError() }
        .build()

    val viewDelegates = listOf(
        CartProductDelegate(
            onClick = viewState::navigateToProductCard,
            onFavoriteCheckedChange = ::changeProductFavoriteState,
            delete = ::removeProductFromCart
        ),
        CartEmptyDelegate(),
        CartPromocodeDelegate(),
        CartHeaderDelegate(viewState::changeAddress),
        CartSummaryDelegate()
    )

    private val compositeDisposable = CompositeDisposable()

    init {
        viewState.showLoading(show = true)
        val pagingDisposable = Pager(
            config = pagerConfig,
            pagingSourceFactory = { getProductsInCartUseCase.handle() }
        ).observable
            .cachedIn(presenterScope)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { data ->
                    viewState.showLoading(show = false)
                    viewState.setAdapter(data)
                },
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
            onError = ::handleException
        )
    }

    private fun changeProductFavoriteState(
        productId: Long,
        isChecked: Boolean,
        isSuccessfulCallback: (Boolean) -> Unit,
    ) {
        productManager.get().changeProductInFavoriteState(
            productId = productId,
            isInFavorite = isChecked,
            onComplete = { isSuccessfulCallback(true) },
            onError = { ex ->
                isSuccessfulCallback(false)
                handleException(exception = ex)
            }
        )
    }

    fun makeOrder() {
        val makeOrderDisposable = makeOrderUseCase.get().handle()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.navigateToThanksScreen()
                viewState.hidePayButton()
                viewState.setAdapter(PagingData.empty())
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