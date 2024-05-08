package ru.point.domain.usecase.interfaces.auth

import io.reactivex.rxjava3.core.Completable

interface RegisterUserUseCase {
    fun handle(
        name: String,
        secondName: String,
        telephone: String,
        email: String,
        username: String,
        secret: String,
        password: String
    ): Completable
}