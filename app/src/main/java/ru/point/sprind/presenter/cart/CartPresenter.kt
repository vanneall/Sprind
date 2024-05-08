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

    fun initAdapter() {
        val disposable = getProductsInCartUseCase
            .handle()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                viewState.setAdapter(list)

                if (list.first() !is CartEmptyVo) {
                    viewState.showPayButton()
                }

            }, { ex ->
                if (ex is HttpException) {
                    when (ex.code()) {
                        403 -> viewState.requireAuthorization()
                    }
                }
            })

        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}