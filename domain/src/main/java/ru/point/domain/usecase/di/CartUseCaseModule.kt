package ru.point.domain.usecase.di

import dagger.Module
import dagger.Provides
import ru.point.domain.repository.CartRepository
import ru.point.domain.usecase.implementation.AddProductToCartUseCaseImpl
import ru.point.domain.usecase.implementation.GetProductDtoFromCartUseCaseImpl
import ru.point.domain.usecase.interfaces.AddProductToCartUseCase
import ru.point.domain.usecase.interfaces.GetProductDtoFromCartUseCase

@Module
class CartUseCaseModule {

    @Provides
    fun provideGetProductFromCart(repository: CartRepository): GetProductDtoFromCartUseCase {
        return GetProductDtoFromCartUseCaseImpl(repository = repository)
    }

    @Provides
    fun providerAddProductToCartUseCase(repository: CartRepository): AddProductToCartUseCase {
        return AddProductToCartUseCaseImpl(repository = repository)
    }
}