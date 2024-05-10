package ru.point.sprind.presenter.product.morda

import dagger.Lazy
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.point.domain.usecase.interfaces.cart.AddProductToCartUseCase
import ru.point.domain.usecase.interfaces.favorite.ChangeFavoriteStateUseCase
import ru.point.domain.usecase.interfaces.product.GetProductsUseCase
import ru.point.sprind.entity.deletage.product.feed.ProductDelegate
import javax.inject.Inject

@InjectViewState
class MordaPresenter @Inject constructor(
    private val addProductToCartUseCase: Lazy<AddProductToCartUseCase>,
    private val getProductsUseCase: GetProductsUseCase,
    private val changeFavoriteStateUseCase: Lazy<ChangeFavoriteStateUseCase>,
) : MvpPresenter<MordaView>() {

    val delegates = listOf(
        ProductDelegate(
            onClickCard = viewState::openCard,
            onBuyClick = ::onAddProductToCart,
            onFavoriteCheckedChange = ::onCheckedFavoriteStateChange
        )
    )

    private val compositeDisposable = CompositeDisposable()

    init {
        viewState.displayLoadingScreen(show = true)
        val disposable = getProductsUseCase.handle()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                viewState.displayLoadingScreen(show = false)
                viewState.setAdapter(views = list)
            }, { ex ->
                viewState.displayLoadingScreen(show = false)
                viewState.displayBadConnectionScreen(show = true)
                ex.printStackTrace()
            })
        compositeDisposable.add(disposable)
    }

    private fun onAddProductToCart(productId: Long) {
        val disposable = addProductToCartUseCase.get().handle(id = productId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({}, { ex ->
                ex.printStackTrace()
                viewState.displaySomethingGoesWrongError()
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
                    ex.printStackTrace()
                    isSuccessfulCallback(false)
                    viewState.displaySomethingGoesWrongError()
                })

        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}