package ru.point.sprind.usecase

import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.entity.deletage.ProductDelegate
import javax.inject.Inject

class GetMordaDelegatesUseCaseImpl @Inject constructor() : GetMordaDelegatesUseCase {
    override fun handle(): List<Delegate> {
        return listOf(ProductDelegate())
    }
}