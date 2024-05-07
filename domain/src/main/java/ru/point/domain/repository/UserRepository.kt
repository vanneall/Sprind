package ru.point.domain.repository

import io.reactivex.rxjava3.core.Single
import ru.point.domain.entity.dto.AuthUser
import ru.point.domain.entity.utils.Token

interface UserRepository {

    fun authorize(user: AuthUser): Single<Token>

}