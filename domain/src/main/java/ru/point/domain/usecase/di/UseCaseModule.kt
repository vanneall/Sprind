package ru.point.domain.usecase.di

import dagger.Module
import dagger.Provides
import ru.point.domain.repository.ProductRepository
import ru.point.domain.usecase.di.implementation.GetProductUseCaseImpl
import ru.point.domain.usecase.interfaces.GetProductsUseCase

@Module
class UseCaseModule {
    @Provides
    fun provideGetProductUseCase(repository: ProductRepository): GetProductsUseCase {
        return GetProductUseCaseImpl(repository = repository)
    }
}