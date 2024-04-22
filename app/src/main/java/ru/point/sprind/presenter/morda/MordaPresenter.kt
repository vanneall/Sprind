package ru.point.sprind.presenter.morda

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.point.domain.entity.FeedProductDto
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
    private var feedProductDtos: List<FeedProductDto> = emptyList()

    private val compositeDisposable = CompositeDisposable()

    init {
        delegates = getMordaDelegatesUseCase.handle()
        val disposable = getProductsUseCase.handle()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                viewState.disableLoadingScreen()
                list.forEach {
                    it.onClick = { viewState.openCard(it.id) }
                }

                feedProductDtos = list
                if (list.isEmpty()) viewState.setNotFound()
                else {
                    viewState.setProductAdapter(feedProductDtos, listOf(ProductDelegate()))
                }
            }, {
                viewState.disableLoadingScreen()
                viewState.setBadConnection()
            }
            )

        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}