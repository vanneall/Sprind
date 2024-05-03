package ru.point.sprind.presenter.result

import dagger.Lazy
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.point.domain.usecase.interfaces.AddProductToCartUseCase
import ru.point.domain.usecase.interfaces.ChangeFavoriteStateUseCase
import ru.point.domain.usecase.interfaces.GetProductsByNameUseCase
import ru.point.sprind.entity.deletage.ProductDelegate
import ru.point.sprind.presenter.morda.MordaView
import ru.point.sprind.presenter.result.ResultPresenterAssistedFactory.Companion.QUERY

@InjectViewState
class ResultPresenter @AssistedInject constructor(
    @Assisted(QUERY) private val query: String,
    private val addProductToCartUseCase: AddProductToCartUseCase,
    private val getProductsByNameUseCase: GetProductsByNameUseCase,
    private val changeFavoriteStateUseCase: Lazy<ChangeFavoriteStateUseCase>,
) : MvpPresenter<MordaView>() {

    private val delegates = listOf(
        ProductDelegate(
            onClickCard = viewState::openCard,
            onBuyClick = addProductToCartUseCase::handle,
            onFavoriteCheckedChange = changeFavoriteStateUseCase.get()::handle
        )
    )

    init {
        getSearchResult()
    }

    fun getSearchResult() {
        val disposable = getProductsByNameUseCase.handle(query)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                viewState.disableLoadingScreen()
                if (list.isEmpty()) {
                    viewState.showNotFoundScreen()
                } else {
                    viewState.setProductAdapter(
                        list,
                        delegates
                    )
                }
            }, {
                it.printStackTrace()
                viewState.disableLoadingScreen()
                viewState.showBadConnectionScreen()
            }
            )
    }

}