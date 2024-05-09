package ru.point.sprind.presenter.cart

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter
import retrofit2.HttpException
import ru.point.domain.entity.view.cart.CartEmptyVo
import ru.point.domain.usecase.interfaces.cart.GetProductsInCartUseCase
import ru.point.sprind.entity.deletage.product.cart.CartEmptyDelegate
import ru.point.sprind.entity.deletage.product.cart.CartProductDelegate
import ru.point.sprind.entity.deletage.product.cart.CartPromocodeDelegate
import ru.point.sprind.entity.deletage.product.cart.CartSummaryDelegate
import javax.inject.Inject

@InjectViewState
class CartPresenter @Inject constructor(
    private val getProductsInCartUseCase: GetProductsInCartUseCase,
) : MvpPresenter<CartView>() {

    val delegates = listOf(
        CartProductDelegate(),
        CartEmptyDelegate(),
        CartPromocodeDelegate(),
        CartSummaryDelegate()
    )

    private val compositeDisposable = CompositeDisposable()

    init {
        viewState.displayLoadingScreen(show = true)
        val disposable = getProductsInCartUseCase
            .handle()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                viewState.displayLoadingScreen(show = false)

                if (list.first() !is CartEmptyVo) {
                    viewState.showPayButton()
                }
                viewState.setAdapter(list)
            }, { ex ->
                viewState.displayLoadingScreen(show = false)
                ex.printStackTrace()
                if (ex is HttpException) {
                    when (ex.code()) {
                        403 -> viewState.requireAuthorization()
                        else -> viewState.displayBadConnectionScreen(show = true)
                    }
                } else {
                    viewState.displayBadConnectionScreen(show = true)
                }
            })

        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}