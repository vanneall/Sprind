package ru.point.sprind.presenter.auth.registration.password

import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory

@AssistedFactory
interface RegistrationPresenterFactory {

    fun create(
        @Assisted(NAME) name: String,
        @Assisted(SECOND_NAME) secondName: String,
        @Assisted(TELEPHONE) telephone: String,
        @Assisted(EMAIL) email: String
    ): RegistrationPresenter

    companion object {
        const val NAME = "name"
        const val SECOND_NAME = "second_name"
        const val TELEPHONE = "telephone"
        const val EMAIL = "email"
    }
}