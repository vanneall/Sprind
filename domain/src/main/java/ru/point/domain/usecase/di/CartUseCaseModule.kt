package ru.point.domain.usecase.di

import dagger.Module
import dagger.Provides
import ru.point.domain.repository.CartRepository
import ru.point.domain.usecase.implementation.cart.AddProductToCartUseCaseImpl
import ru.point.domain.usecase.implementation.cart.DeleteProductFromCartUseCaseImpl
import ru.point.domain.usecase.implementation.cart.GetProductsInCartUseCaseImpl
import ru.point.domain.usecase.implementation.cart.MakeOrderUseCaseImpl
import ru.point.domain.usecase.interfaces.cart.AddProductToCartUseCase
import ru.point.domain.usecase.interfaces.cart.DeleteProductFromCartUseCase
import ru.point.domain.usecase.interfaces.cart.GetProductsInCartUseCase
import ru.point.domain.usecase.interfaces.cart.MakeOrderUseCase

@Module
class CartUseCaseModule {

    @Provides
    fun provideGetProductFromCart(repository: CartRepository): GetProductsInCartUseCase {
        return GetProductsInCartUseCaseImpl(repository = repository)
    }

    @Provides
    fun provideAddProductToCartUseCase(repository: CartRepository): AddProductToCartUseCase {
        return AddProductToCartUseCaseImpl(repository = repository)
    }

    @Provides
    fun provideMakeOrderUseCase(repository: CartRepository): MakeOrderUseCase {
        return MakeOrderUseCaseImpl(repository = repository)
    }

    @Provides
    fun provideDeleteProductFromCartUseCase(repository: CartRepository): DeleteProductFromCartUseCase {
        return DeleteProductFromCartUseCaseImpl(repository = repository)
    }
}