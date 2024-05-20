package ru.point.domain.factory.implementation

import ru.point.domain.entity.dto.user.RegisterUserDto
import ru.point.domain.factory.interfaces.RegisterUserFactory
import javax.inject.Inject

class RegisterUserFactoryImpl @Inject constructor(): RegisterUserFactory {
    override fun create(
        name: String,
        secondName: String,
        telephone: String,
        email: String,
        username: String,
        secret: String,
        password: String,
    ): RegisterUserDto {
        return RegisterUserDto(
            name = name,
            secondName = secondName,
            email = email,
            username = username,
            secret = secret,
            password = password
        )
    }
}