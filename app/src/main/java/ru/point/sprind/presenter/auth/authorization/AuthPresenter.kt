package ru.point.sprind.presenter.auth.authorization

import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.point.domain.usecase.interfaces.AuthorizeUseCase
import javax.inject.Inject

@InjectViewState
class AuthPresenter @Inject constructor(
    private val authorizeUseCase: AuthorizeUseCase,
) : MvpPresenter<AuthView>() {

    private val compositeDisposable = CompositeDisposable()

    fun auth(username: String, password: String) {
        val disposable = authorizeUseCase.handle(username = username, password = password)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.v("Auth", it.value)
                viewState.successfulAuthorization()
            }, {
                it.printStackTrace()
            })

        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}