package ru.point.sprind.di.usecases

import dagger.Module
import dagger.Provides
import ru.point.domain.repository.CategoryRepository
import ru.point.domain.usecase.implementation.category.GetCategoryProductsPageUseCaseImpl
import ru.point.domain.usecase.interfaces.category.GetCategoryProductsPageUseCase

@Module
class CategoryUseCaseModule {
    @Provides
    fun provideGetProductsPageUseCase(repository: CategoryRepository): GetCategoryProductsPageUseCase {
        return GetCategoryProductsPageUseCaseImpl(repository = repository)
    }
}