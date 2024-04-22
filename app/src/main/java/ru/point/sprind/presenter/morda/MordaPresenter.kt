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
    private val getProductsUseCase: GetProductsUseCase,
    private val getMordaDelegatesUseCase: GetMordaDelegatesUseCase,
) : MvpPresenter<MordaView>() {

    private var delegates: List<Delegate> = emptyList()
    private var feedProductDtos: List<FeedProductDto> = emptyList()

    private val compositeDisposable = CompositeDisposable()

    init {
        delegates = getMordaDelegatesUseCase.handle()

        viewState.showLoadingScreen()
        val disposable = getProductsUseCase
            .handle()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { list ->
                    viewState.disableLoadingScreen()
                    feedProductDtos = list

                    viewState.setProductAdapter(
                        delegates = delegates,
                        views = feedProductDtos
                    )
                }, {
                    viewState.disableLoadingScreen()
                    viewState.showBadConnectionScreen()
                }
            )
        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}