package ru.point.sprind.presenter.search

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.point.domain.entity.view.search.SearchRequestVo
import ru.point.manager.RequestHistoryManager
import ru.point.sprind.entity.deletage.product.request.RequestDelegate
import javax.inject.Inject

@InjectViewState
class SearchPresenter @Inject constructor(
    private val manager: RequestHistoryManager,
) : MvpPresenter<SearchRequestView>() {

    val delegates = listOf(RequestDelegate())

    private val compositeDisposable = CompositeDisposable()

    fun addRequestToHistory(request: String) {
        manager.insert(request = request)
    }


    fun getRequests() {
        val disposable = manager.getAll()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                val views = list.mapIndexed { index, entity -> SearchRequestVo(index.toLong(), entity) }
                viewState.setAdapter(views.reversed())
            }, { ex ->
                ex.printStackTrace()
            })

        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

}