package ru.point.domain.usecase.implementation

import io.reactivex.rxjava3.core.Single
import ru.point.domain.entity.dto.AuthUser
import ru.point.domain.entity.utils.Token
import ru.point.domain.repository.UserRepository
import ru.point.domain.usecase.interfaces.AuthorizeUseCase
import javax.inject.Inject

class AuthorizeUseCaseImpl @Inject constructor(
    private val repository: UserRepository,
) : AuthorizeUseCase {
    override fun handle(username: String, password: String): Single<Token> {
        val authUser = AuthUser(username = username, password = password)

        return repository.authorize(user = authUser)
    }
}