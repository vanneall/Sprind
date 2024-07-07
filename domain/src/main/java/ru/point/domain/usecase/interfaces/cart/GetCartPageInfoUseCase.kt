package ru.point.domain.usecase.interfaces.cart

import io.reactivex.rxjava3.core.Single
import ru.point.domain.entity.complex.CartInfoVoContainer

interface GetCartPageInfoUseCase {
    fun handle(): Single<CartInfoVoContainer>
}