package ru.point.sprind.presenter.search

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.point.domain.entity.Request
import ru.point.domain.usecase.interfaces.GetProductsByNameUseCase
import ru.point.sprind.entity.deletage.RequestDelegate
import javax.inject.Inject

@InjectViewState
class SearchPresenter @Inject constructor(
    private val getProductsByNameUseCase: GetProductsByNameUseCase,
) : MvpPresenter<SearchView>() {

    fun getByName(name: String) {
        val disposable = getProductsByNameUseCase
            .handle(name)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                viewState.setAdapter(
                    listOf(RequestDelegate()),
                    list.map { Request(id = 1, text = it.name, onClick = viewState::openResult) }
                )
            }, { })
    }
}