package ru.point.domain.usecase.implementation.auth

import io.reactivex.rxjava3.core.Completable
import ru.point.domain.factory.interfaces.RegisterUserFactory
import ru.point.domain.repository.UserRepository
import ru.point.domain.usecase.interfaces.auth.RegisterUserUseCase

class RegisterUserUseCaseImpl(
    private val factory: RegisterUserFactory,
    private val repository: UserRepository
) : RegisterUserUseCase {
    override fun handle(
        name: String,
        secondName: String,
        telephone: String,
        email: String,
        username: String,
        secret: String,
        password: String,
    ): Completable {
        val regUser = factory.create(
            name = name,
            secondName = secondName,
            telephone = telephone,
            email = email,
            secret = secret,
            password = password,
            username = username
        )

        return repository.register(user = regUser)
    }
}