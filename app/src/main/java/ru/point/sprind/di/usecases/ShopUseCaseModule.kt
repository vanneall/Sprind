package ru.point.sprind.di.usecases

import dagger.Module
import dagger.Provides
import ru.point.domain.paging.ShopPagingSource
import ru.point.domain.usecase.implementation.shop.GetShopProductsUseCaseImpl
import ru.point.domain.usecase.interfaces.shop.GetShopProductsUseCase

@Module
class ShopUseCaseModule {
    @Provides
    fun provideGetShopProductsUseCase(pagingSourceFactory: ShopPagingSource.Factory): GetShopProductsUseCase {
        return GetShopProductsUseCaseImpl(pagingSourceFactory = pagingSourceFactory)
    }
}