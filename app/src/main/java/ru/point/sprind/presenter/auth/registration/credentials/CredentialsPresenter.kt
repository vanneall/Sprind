package ru.point.sprind.presenter.auth.registration.credentials

import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class CredentialsPresenter @Inject constructor() : MvpPresenter<CredentialsView>() {

    fun isDataValid(name: String, secondName: String, telephone: String, email: String): Boolean {
        val isNameCorrect = name.length > 2
        val isSecondNameCorrect = secondName.isNotEmpty()
        val isTelephoneCorrect = telephone.isNotEmpty()
        val isEmailCorrect = email.contains('@')

        return isNameCorrect && isSecondNameCorrect && isTelephoneCorrect && isEmailCorrect
    }

}