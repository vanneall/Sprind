package ru.point.sprind.presenter.profile.main

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.point.domain.entity.utils.Token
import ru.point.domain.usecase.interfaces.profile.GetUserInfoUseCase
import ru.point.manager.SettingsManager
import javax.inject.Inject

@InjectViewState
class ProfilePresenter @Inject constructor(
    getUserInfoUseCase: GetUserInfoUseCase,
    private val settingsManager: SettingsManager
) : MvpPresenter<ProfileView>() {

    private val compositeDisposable = CompositeDisposable()

    init {
        val disposable = getUserInfoUseCase.handle()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ user ->
                viewState.setUsername(user.name + " " + user.secondName)
            }, {
                viewState.setUsername("")
            })

        compositeDisposable.add(disposable)
        viewState.setIsDarkThemeEnabled(settingsManager.isDarkThemeEnabled)
    }

    fun logout() {
        settingsManager.token = Token("")
    }

    fun switchTheme(isDark: Boolean) {
        settingsManager.isDarkThemeEnabled = isDark
        viewState.setIsDarkThemeEnabled(isDark)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}