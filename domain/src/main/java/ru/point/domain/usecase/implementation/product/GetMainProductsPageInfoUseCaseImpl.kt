package ru.point.domain.usecase.implementation.product

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.point.domain.entity.complex.ComplexProductFeedVo
import ru.point.domain.entity.dto.complex.toComplexProductFeedVo
import ru.point.domain.repository.ProductRepository
import ru.point.domain.usecase.interfaces.product.GetMainProductsPageInfoUseCase
import javax.inject.Inject

class GetMainProductsPageInfoUseCaseImpl @Inject constructor(
    private val remoteRepository: ProductRepository
) : GetMainProductsPageInfoUseCase {
    override fun handle(): Single<ComplexProductFeedVo> {
        return remoteRepository.getMainPageInfo()
            .observeOn(Schedulers.computation())
            .map { response -> response.toComplexProductFeedVo() }
    }
}