package ru.point.domain.factory

import ru.point.domain.entity.dto.RegisterUser
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
    ): RegisterUser {
        return RegisterUser(
            name = name,
            secondName = secondName,
            telephone = telephone,
            email = email,
            username = username,
            secret = secret,
            password = password
        )
    }
}