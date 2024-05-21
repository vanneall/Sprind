package ru.point.sprind.presenter.product.result

import dagger.Lazy
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter
import retrofit2.HttpException
import ru.point.domain.usecase.interfaces.cart.AddProductToCartUseCase
import ru.point.domain.usecase.interfaces.favorite.ChangeFavoriteStateUseCase
import ru.point.domain.usecase.interfaces.product.GetProductsByNameUseCase
import ru.point.sprind.entity.deletage.product.feed.ProductDelegate
import ru.point.sprind.entity.manager.HttpExceptionStatusManager
import ru.point.sprind.presenter.product.morda.MordaView
import ru.point.sprind.presenter.product.result.ResultPresenterAssistedFactory.Companion.QUERY

@InjectViewState
class ResultPresenter @AssistedInject constructor(
    @Assisted(QUERY) private val query: String,
    private val addProductToCartUseCase: Lazy<AddProductToCartUseCase>,
    private val getProductsByNameUseCase: GetProductsByNameUseCase,
    private val changeFavoriteStateUseCase: Lazy<ChangeFavoriteStateUseCase>,
) : MvpPresenter<MordaView>() {

    private val httpManager = HttpExceptionStatusManager
        .Builder()
        .add403ExceptionHandler { viewState.requireAuthorization() }
        .addDefaultExceptionHandler { viewState.displaySomethingGoesWrongError() }
        .build()

    val delegates = listOf(
        ProductDelegate(
            onClickCard = viewState::openCard,
            onBuyClick = ::onAddProductToCart,
            onFavoriteCheckedChange = ::onCheckedFavoriteStateChange
        )
    )

    private val compositeDisposable = CompositeDisposable()

    fun init() {
        viewState.displayLoadingScreen(show = true)
        val disposable = getProductsByNameUseCase.handle(query)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ vo ->
                viewState.displayLoadingScreen(show = false)
                viewState.setAddress(vo.addressVo.address)
                viewState.setAdapter(views = vo.productsVo)
            }, { ex ->
                viewState.displayLoadingScreen(show = false)
                viewState.displayBadConnectionScreen(show = true)
                if (ex is HttpException) httpManager.handle(ex)
            })
        compositeDisposable.add(disposable)
    }


    private fun onAddProductToCart(productId: Long) {
        val disposable = addProductToCartUseCase.get().handle(id = productId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({}, { ex ->
                ex.printStackTrace()
                if (ex is HttpException) {
                    httpManager.handle(ex)
                }
            })

        compositeDisposable.add(disposable);
    }

    private fun onCheckedFavoriteStateChange(
        productId: Long,
        isChecked: Boolean,
        isSuccessfulCallback: (Boolean) -> Unit,
    ) {
        val disposable =
            changeFavoriteStateUseCase.get().handle(id = productId, isFavorite = isChecked)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    isSuccessfulCallback(true)
                }, { ex ->
                    isSuccessfulCallback(false)
                    if (ex is HttpException) {
                        httpManager.handle(ex)
                    }
                })

        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}