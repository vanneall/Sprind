package ru.point.sprind.presenter.auth.registration.password

import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.point.domain.usecase.interfaces.RegisterUserUseCase
import ru.point.sprind.presenter.auth.registration.password.RegistrationPresenterFactory.Companion.EMAIL
import ru.point.sprind.presenter.auth.registration.password.RegistrationPresenterFactory.Companion.NAME
import ru.point.sprind.presenter.auth.registration.password.RegistrationPresenterFactory.Companion.SECOND_NAME
import ru.point.sprind.presenter.auth.registration.password.RegistrationPresenterFactory.Companion.TELEPHONE

@InjectViewState
class RegistrationPresenter @AssistedInject constructor(
    @Assisted(NAME) private val name: String,
    @Assisted(SECOND_NAME) private val secondName: String,
    @Assisted(TELEPHONE) private val telephone: String,
    @Assisted(EMAIL) private val email: String,
    private val registerUserUseCase: RegisterUserUseCase,
) : MvpPresenter<RegistrationView>() {

    private val compositeDisposable = CompositeDisposable()

    fun register(username: String, secret: String, password: String) {
        val isUsernameCorrect = username.length > 2
        val isSecretCorrect = secret.isNotEmpty()
        val isPasswordCorrect = password.isNotEmpty()

        if (!(isUsernameCorrect && isSecretCorrect && isPasswordCorrect)) {
            viewState.showError()
            return
        }

        val disposable = registerUserUseCase.handle(
            name = name,
            secondName = secondName,
            telephone = telephone,
            email = email,
            username = username,
            secret = secret,
            password = password
        ).observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.exitFromRegistration()
            }, {
                viewState.showError()
                it.printStackTrace()
            })

        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

}