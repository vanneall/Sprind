package ru.point.sprind.presenter.profile.orders

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter
import retrofit2.HttpException
import ru.point.domain.usecase.interfaces.profile.GetOrdersUseCase
import ru.point.sprind.entity.deletage.order.OrderDelegate
import ru.point.sprind.entity.manager.HttpExceptionStatusManager
import javax.inject.Inject

@InjectViewState
class OrdersPresenter @Inject constructor(
    getOrdersUseCase: GetOrdersUseCase
) : MvpPresenter<OrdersViewDefault>() {

    private val httpManager = HttpExceptionStatusManager
        .Builder()
        .add403ExceptionHandler { viewState::requireAuthorization }
        .addDefaultExceptionHandler { viewState::showSomethingGoesWrongError }
        .build()

    val delegates = listOf(
        OrderDelegate()
    )

    private val compositeDisposable = CompositeDisposable()

    init {
        viewState.showLoading(show = true)
        val disposable = getOrdersUseCase.handle()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                viewState.showLoading(show = false)
                viewState.setAdapter(views = list)
            }, { ex ->
                viewState.showLoading(show = false)
                viewState.showBadConnection(show = true)
                if (ex is HttpException) httpManager.handle(ex)
            })

        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}