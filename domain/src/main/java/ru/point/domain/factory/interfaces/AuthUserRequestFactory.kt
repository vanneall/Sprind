package ru.point.domain.factory.interfaces

import ru.point.domain.entity.request.user.AuthUserRequest

interface AuthUserRequestFactory {
    fun create(username: String, password: String): AuthUserRequest
}