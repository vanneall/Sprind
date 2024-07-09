package ru.point.sprind.presenter.cart

import androidx.paging.Pager
import androidx.paging.PagingConfig
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
import ru.point.domain.usecase.interfaces.cart.RemoveProductFromCartUseCase
import ru.point.domain.usecase.interfaces.cart.GetCartPageInfoUseCase
import ru.point.domain.usecase.interfaces.cart.GetProductsInCartUseCase
import ru.point.domain.usecase.interfaces.cart.MakeOrderUseCase
import ru.point.domain.usecase.interfaces.favorite.ChangeFavoriteStateUseCase
import ru.point.sprind.entity.deletage.product.cart.CartEmptyDelegate
import ru.point.sprind.entity.deletage.product.cart.CartHeaderDelegate
import ru.point.sprind.entity.deletage.product.cart.CartProductDelegate
import ru.point.sprind.entity.deletage.product.cart.CartPromocodeDelegate
import ru.point.sprind.entity.deletage.product.cart.CartSummaryDelegate
import ru.point.sprind.entity.manager.HttpExceptionStatusManager
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@InjectViewState
class CartPresenter @Inject constructor(
    private val getProductsInCartUseCase: GetProductsInCartUseCase,
    private val makeOrderUseCase: Lazy<MakeOrderUseCase>,
    private val getCartPageInfoUseCase: GetCartPageInfoUseCase,
    private val changeFavoriteStateUseCase: Lazy<ChangeFavoriteStateUseCase>,
    private val removeProductFromCartUseCase: Lazy<RemoveProductFromCartUseCase>,
) : MvpPresenter<CartView>() {

    private val httpManager = HttpExceptionStatusManager.Builder()
        .add403ExceptionHandler { viewState.requireAuthorization() }
        .addDefaultExceptionHandler { viewState.showSomethingGoesWrongError() }.build()

    val delegates = listOf(
        CartProductDelegate(
            onClick = viewState::openCard,
            onFavoriteCheckedChange = ::onCheckedFavoriteStateChange,
            delete = ::deleteFromCart
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
            config = PagingConfig(
                pageSize = 25,
                prefetchDistance = 10,
                maxSize = 45,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { getProductsInCartUseCase.handle() }
        ).observable
            .cachedIn(presenterScope)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data ->
                viewState.showLoading(show = false)
                viewState.displayPayButton(true)
                viewState.setAdapter(data)
            }, { ex ->
                viewState.showLoading(show = false)
                if (ex is HttpException) httpManager.handle(ex)
                else viewState.showBadConnection(show = true)
            })

        compositeDisposable.add(pagingDisposable)
    }

    private fun deleteFromCart(id: Long) {
        val disposable = removeProductFromCartUseCase.get().handle(id)
            .subscribeOn(AndroidSchedulers.mainThread()).subscribe({}, { ex ->
                if (ex is HttpException) httpManager.handle(ex)
            })

        compositeDisposable.add(disposable)
    }

    private fun onCheckedFavoriteStateChange(
        productId: Long,
        isChecked: Boolean,
        isSuccessfulCallback: (Boolean) -> Unit,
    ) {
        val disposable =
            changeFavoriteStateUseCase.get().handle(id = productId, isFavorite = isChecked)
                .observeOn(AndroidSchedulers.mainThread()).subscribe({
                    isSuccessfulCallback(true)
                }, { ex ->
                    isSuccessfulCallback(false)
                    if (ex is HttpException) httpManager.handle(ex)
                })

        compositeDisposable.add(disposable)
    }

    fun makeOrder() {
        val disposable =
            makeOrderUseCase.get().handle().observeOn(AndroidSchedulers.mainThread()).subscribe({
                viewState.openThanksScreen()
                viewState.setAdapter(PagingData.empty())
                viewState.displayPayButton(false)
            }, { ex ->
                if (ex is HttpException) httpManager.handle(ex)
            })

        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}