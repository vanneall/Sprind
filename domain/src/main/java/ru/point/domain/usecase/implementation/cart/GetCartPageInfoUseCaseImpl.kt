package ru.point.domain.usecase.implementation.cart

import io.reactivex.rxjava3.schedulers.Schedulers
import ru.point.domain.entity.complex.CartInfoVoContainer
import ru.point.domain.entity.response.mappers.toAddressVo
import ru.point.domain.entity.response.mappers.toCartSummaryVo
import ru.point.domain.repository.CartRepository
import ru.point.domain.usecase.interfaces.cart.GetCartPageInfoUseCase

class GetCartPageInfoUseCaseImpl(
    private val repository: CartRepository
) : GetCartPageInfoUseCase {
    override fun handle() = repository.getPageInfo()
        .observeOn(Schedulers.computation())
        .map { info ->
            CartInfoVoContainer(
                addressVo = info.addressInfoResponse.toAddressVo(),
                orderSummaryVo = info.summaryPriceResponse?.toCartSummaryVo()
            )
        }
}