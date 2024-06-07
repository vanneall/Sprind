package ru.point.sprind.presenter.cart

import dagger.Lazy
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter
import retrofit2.HttpException
import ru.point.domain.entity.view.cart.CartEmptyVo
import ru.point.domain.usecase.interfaces.cart.DeleteProductFromCartUseCase
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

@InjectViewState
class CartPresenter @Inject constructor(
    private val getProductsInCartUseCase: GetProductsInCartUseCase,
    private val makeOrderUseCase: Lazy<MakeOrderUseCase>,
    private val changeFavoriteStateUseCase: Lazy<ChangeFavoriteStateUseCase>,
    private val deleteProductFromCartUseCase: Lazy<DeleteProductFromCartUseCase>,
) : MvpPresenter<CartView>() {

    private val httpManager = HttpExceptionStatusManager
        .Builder()
        .add403ExceptionHandler { viewState::requireAuthorization }
        .addDefaultExceptionHandler { viewState::displaySomethingGoesWrongError }
        .build()

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

    fun getCartProducts() {
        viewState.displayLoadingScreen(show = true)
        val disposable = getProductsInCartUseCase
            .handle()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ dto ->
                viewState.displayLoadingScreen(show = false)

                if (dto.isCartEmpty) {
                    viewState.displayPayButton(false)
                } else {
                    if (!dto.isAddressEmpty) viewState.displayPayButton(true)
                }
                viewState.setAdapter(dto.productsVo)
            }, { ex ->
                viewState.displayLoadingScreen(show = false)
                if (ex is HttpException) httpManager.handle(ex)
                else viewState.displayBadConnectionScreen(show = true)
            })

        compositeDisposable.add(disposable)
    }

    private fun deleteFromCart(id: Long) {
        val disposable = deleteProductFromCartUseCase.get()
            .handle(id)
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {}, { ex ->
                    if (ex is HttpException) httpManager.handle(ex)
                })

        compositeDisposable.add(disposable)
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

    fun makeOrder() {
        val disposable = makeOrderUseCase.get().handle()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.openThanksScreen()
                viewState.setAdapter(listOf(CartEmptyVo()))
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