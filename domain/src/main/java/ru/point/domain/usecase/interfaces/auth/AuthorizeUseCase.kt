package ru.point.domain.usecase.interfaces.auth

import io.reactivex.rxjava3.core.Single
import ru.point.domain.entity.utils.Token

interface AuthorizeUseCase {
    fun handle(username: String, password: String): Single<Token>
}