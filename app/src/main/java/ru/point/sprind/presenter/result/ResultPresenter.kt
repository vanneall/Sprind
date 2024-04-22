package ru.point.sprind.presenter.result

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.point.domain.usecase.interfaces.GetProductsByNameUseCase
import ru.point.sprind.entity.deletage.ProductDelegate
import ru.point.sprind.presenter.morda.MordaView
import javax.inject.Inject

@InjectViewState
class ResultPresenter @Inject constructor(
    private val getProductsByNameUseCase: GetProductsByNameUseCase,
) : MvpPresenter<MordaView>() {

    fun getSearchResult(result: String) {
        val disposable = getProductsByNameUseCase.handle(result)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                viewState.disableLoadingScreen()
                if (list.isEmpty()) {
                    viewState.showNotFoundScreen()
                } else {
                    viewState.setProductAdapter(list, listOf(ProductDelegate()))
                }
            }, {
                viewState.disableLoadingScreen()
                viewState.showBadConnectionScreen()
            }
            )
    }

}