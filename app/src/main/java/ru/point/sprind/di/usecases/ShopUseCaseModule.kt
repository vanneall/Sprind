package ru.point.sprind.di.usecases

import dagger.Module
import dagger.Provides
import ru.point.domain.repository.ShopRepository
import ru.point.domain.usecase.implementation.shop.GetShopProductsUseCaseImpl
import ru.point.domain.usecase.interfaces.shop.GetShopProductsUseCase

@Module
class ShopUseCaseModule {
    @Provides
    fun provideGetShopProductsUseCase(repository: ShopRepository): GetShopProductsUseCase {
        return GetShopProductsUseCaseImpl(repository = repository)
    }
}