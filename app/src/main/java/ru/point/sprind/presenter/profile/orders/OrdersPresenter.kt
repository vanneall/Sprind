package ru.point.sprind.presenter.profile.orders

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.point.domain.usecase.interfaces.profile.GetOrdersUseCase
import ru.point.sprind.entity.deletage.order.OrderDelegate
import javax.inject.Inject

@InjectViewState
class OrdersPresenter @Inject constructor(
    getOrdersUseCase: GetOrdersUseCase
) : MvpPresenter<OrdersView>() {

    val delegates = listOf(
        OrderDelegate()
    )

    private val compositeDisposable = CompositeDisposable()

    init {
        viewState.displayLoadingScreen(show = true)
        val disposable = getOrdersUseCase.handle()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                viewState.displayLoadingScreen(show = false)
                viewState.setAdapter(views = list)
            }, { ex ->
                viewState.displayLoadingScreen(show = false)
                viewState.displayBadConnectionScreen(show = true)
                ex.printStackTrace()
            })

        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}