package ru.point.domain.factory.implementation

import ru.point.domain.entity.request.user.AuthUserRequest
import ru.point.domain.factory.interfaces.AuthUserRequestFactory

class AuthUserRequestFactoryImpl : AuthUserRequestFactory {
    override fun create(username: String, password: String) = AuthUserRequest(
        username = username,
        password = password
    )
}