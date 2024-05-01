package ru.point.sprind.presenter.cart

import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.point.domain.entity.view.CartEmptyVo
import ru.point.domain.usecase.interfaces.GetProductDtoFromCartUseCase
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.entity.deletage.product.cart.CartEmptyDelegate
import ru.point.sprind.entity.deletage.product.cart.CartProductDelegate
import ru.point.sprind.entity.deletage.product.cart.CartPromocodeDelegate
import ru.point.sprind.entity.deletage.product.cart.CartSummaryDelegate
import javax.inject.Inject

@InjectViewState
class CartPresenter @Inject constructor(
    private val getProductDtoFromCartUseCase: GetProductDtoFromCartUseCase,
) : MvpPresenter<CartView>() {

    val delegates: List<Delegate> = listOf(
        CartProductDelegate(),
        CartEmptyDelegate(),
        CartPromocodeDelegate(),
        CartSummaryDelegate()
    )

    private val compositeDisposable = CompositeDisposable()

    fun initAdapter() {
        val disposable = getProductDtoFromCartUseCase
            .handle()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                viewState.setAdapter(list)

                if (list.first() !is CartEmptyVo) {
                    viewState.showPayButton()
                }

            }, {
                Log.e("CART ERROR", it.stackTraceToString())
            })

        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}