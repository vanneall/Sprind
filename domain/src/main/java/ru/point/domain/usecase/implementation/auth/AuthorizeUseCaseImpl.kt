package ru.point.domain.usecase.implementation.auth

import io.reactivex.rxjava3.core.Single
import ru.point.domain.entity.utils.Token
import ru.point.domain.factory.interfaces.AuthUserRequestFactory
import ru.point.domain.repository.UserRepository
import ru.point.domain.usecase.interfaces.auth.AuthorizeUseCase

class AuthorizeUseCaseImpl(
    private val repository: UserRepository,
    private val factory: AuthUserRequestFactory
) : AuthorizeUseCase {
    override fun handle(username: String, password: String): Single<Token> {
        val authUser = factory.create(username = username, password = password)
        return repository.authorize(user = authUser)
    }
}