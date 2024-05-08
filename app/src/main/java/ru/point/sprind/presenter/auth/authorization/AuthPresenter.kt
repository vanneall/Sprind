package ru.point.sprind.presenter.auth.authorization

import android.util.Log
import dagger.Lazy
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.point.domain.usecase.interfaces.AuthorizeUseCase
import ru.point.storage.SettingsManager
import javax.inject.Inject

@InjectViewState
class AuthPresenter @Inject constructor(
    private val authorizeUseCase: Lazy<AuthorizeUseCase>,
    private val settingsManager: SettingsManager
) : MvpPresenter<AuthView>() {

    private val compositeDisposable = CompositeDisposable()

    init {
        Log.i("Token", "Token in data store before getting token: ${settingsManager.token.value}")
    }

    fun auth(username: String, password: String) {
        val disposable = authorizeUseCase.get().handle(username = username, password = password)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.v("Auth", it.value)
                viewState.successfulAuthorization()
                settingsManager.token = it
                Log.i("Token", "Token in data store after getting token: ${settingsManager.token.value}")
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