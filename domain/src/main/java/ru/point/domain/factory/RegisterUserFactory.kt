package ru.point.domain.factory

import ru.point.domain.entity.dto.RegisterUser

interface RegisterUserFactory {
    fun create(
        name: String,
        secondName: String,
        telephone: String,
        email: String,
        username: String,
        secret: String,
        password: String,
    ): RegisterUser
}
