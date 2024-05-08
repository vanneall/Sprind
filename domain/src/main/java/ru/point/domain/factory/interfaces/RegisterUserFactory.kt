package ru.point.domain.factory.interfaces

import ru.point.domain.entity.dto.user.RegisterUserDto

interface RegisterUserFactory {
    fun create(
        name: String,
        secondName: String,
        telephone: String,
        email: String,
        username: String,
        secret: String,
        password: String,
    ): RegisterUserDto
}
