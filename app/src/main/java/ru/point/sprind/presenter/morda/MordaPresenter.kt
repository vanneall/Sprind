package ru.point.sprind.presenter.morda

import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.point.domain.entity.Product
import ru.point.domain.usecase.interfaces.GetProductsUseCase
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.entity.deletage.ProductDelegate
import ru.point.sprind.usecase.GetMordaDelegatesUseCase
import javax.inject.Inject

@InjectViewState
class MordaPresenter @Inject constructor(
    getProductsUseCase: GetProductsUseCase,
    getMordaDelegatesUseCase: GetMordaDelegatesUseCase,
) : MvpPresenter<MordaView>() {

    private var delegates: List<Delegate> = emptyList()
    private var products: List<Product> = emptyList()

    private val compositeDisposable = CompositeDisposable()

    init {
        delegates = getMordaDelegatesUseCase.handle()
        val disposable = getProductsUseCase.handle()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                products = list
                viewState.setProductAdapter(products, listOf(ProductDelegate()))
            }, {
                Log.d("Retrofit exception", "Error in connection")
            }
            )

        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}