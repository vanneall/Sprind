package ru.point.domain.factory.implementation

import ru.point.domain.entity.request.user.RegisterUserRequest
import ru.point.domain.factory.interfaces.NewUserFactory

class NewUserFactoryImpl : NewUserFactory {
    override fun create(
        name: String,
        secondName: String,
        telephone: String,
        email: String,
        username: String,
        secret: String,
        password: String,
    ) = RegisterUserRequest(
        name = name,
        secondName = secondName,
        email = email,
        username = username,
        secret = secret,
        password = password
    )
}