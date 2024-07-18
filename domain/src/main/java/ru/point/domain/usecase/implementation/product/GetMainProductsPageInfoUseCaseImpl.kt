package ru.point.domain.usecase.implementation.product

import io.reactivex.rxjava3.schedulers.Schedulers
import ru.point.domain.entity.response.mappers.toComplexProductFeedVo
import ru.point.domain.repository.ProductRepository
import ru.point.domain.usecase.interfaces.product.GetMainProductsPageInfoUseCase

class GetMainProductsPageInfoUseCaseImpl(
    private val remoteRepository: ProductRepository
) : GetMainProductsPageInfoUseCase {
    override fun handle() = remoteRepository.getMainPageInfo()
        .observeOn(Schedulers.computation())
        .map { response -> response.toComplexProductFeedVo() }
}