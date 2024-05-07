package ru.point.domain.repository

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.point.domain.entity.dto.AuthUser
import ru.point.domain.entity.dto.RegisterUser
import ru.point.domain.entity.utils.Token

interface UserRepository {

    fun authorize(user: AuthUser): Single<Token>

    fun register(user: RegisterUser): Completable
}