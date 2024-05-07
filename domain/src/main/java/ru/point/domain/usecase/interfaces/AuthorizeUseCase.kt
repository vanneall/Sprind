package ru.point.domain.usecase.interfaces

import io.reactivex.rxjava3.core.Single
import ru.point.domain.entity.utils.Token

interface AuthorizeUseCase {
    fun handle(username: String, password: String): Single<Token>
}