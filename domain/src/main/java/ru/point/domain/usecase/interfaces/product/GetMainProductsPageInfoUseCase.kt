package ru.point.domain.usecase.interfaces.product

import io.reactivex.rxjava3.core.Single
import ru.point.domain.entity.complex.ComplexProductFeedVo

interface GetMainProductsPageInfoUseCase {
    fun handle(): Single<ComplexProductFeedVo>
}