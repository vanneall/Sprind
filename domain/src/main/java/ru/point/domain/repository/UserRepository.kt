package ru.point.domain.repository

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.point.domain.entity.dto.user.AuthUserDto
import ru.point.domain.entity.dto.user.RegisterUserDto
import ru.point.domain.entity.utils.Token

interface UserRepository {

    fun authorize(user: AuthUserDto): Single<Token>

    fun register(user: RegisterUserDto): Completable
}