package ru.point.domain.usecase.di

import dagger.Module
import dagger.Provides
import ru.point.domain.repository.CartRepository
import ru.point.domain.usecase.implementation.cart.AddProductToCartUseCaseImpl
import ru.point.domain.usecase.implementation.cart.GetProductsInCartUseCaseImpl
import ru.point.domain.usecase.interfaces.cart.AddProductToCartUseCase
import ru.point.domain.usecase.interfaces.cart.GetProductsInCartUseCase

@Module
class CartUseCaseModule {

    @Provides
    fun provideGetProductFromCart(repository: CartRepository): GetProductsInCartUseCase {
        return GetProductsInCartUseCaseImpl(repository = repository)
    }

    @Provides
    fun providerAddProductToCartUseCase(repository: CartRepository): AddProductToCartUseCase {
        return AddProductToCartUseCaseImpl(repository = repository)
    }
}