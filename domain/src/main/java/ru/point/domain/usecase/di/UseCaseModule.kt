package ru.point.domain.usecase.di

import dagger.Module
import dagger.Provides
import ru.point.domain.repository.ProductRepository
import ru.point.domain.usecase.implementation.GetProductUseCaseImpl
import ru.point.domain.usecase.implementation.GetProductsByNameUseCaseImpl
import ru.point.domain.usecase.interfaces.GetProductsByNameUseCase
import ru.point.domain.usecase.interfaces.GetProductsUseCase

@Module
class UseCaseModule {
    @Provides
    fun provideGetProductUseCase(repository: ProductRepository): GetProductsUseCase {
        return GetProductUseCaseImpl(repository = repository)
    }

    @Provides
    fun provideGetProductByNameUseCase(repository: ProductRepository): GetProductsByNameUseCase {
        return GetProductsByNameUseCaseImpl(repository = repository)
    }
}