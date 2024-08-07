package ru.point.domain.factory.interfaces

import ru.point.domain.entity.request.user.RegisterUserRequest

interface NewUserFactory {
    fun create(
        name: String,
        secondName: String,
        telephone: String,
        email: String,
        username: String,
        secret: String,
        password: String,
    ): RegisterUserRequest
}
