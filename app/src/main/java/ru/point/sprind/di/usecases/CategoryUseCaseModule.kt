package ru.point.sprind.di.usecases

import dagger.Module
import dagger.Provides
import ru.point.domain.paging.CategoryPagingSource
import ru.point.domain.usecase.implementation.category.GetCategoryProductsPageUseCaseImpl
import ru.point.domain.usecase.interfaces.category.GetCategoryProductsPageUseCase

@Module
class CategoryUseCaseModule {
    @Provides
    fun provideGetProductsPageUseCase(factory: CategoryPagingSource.Factory): GetCategoryProductsPageUseCase {
        return GetCategoryProductsPageUseCaseImpl(pagingSourceFactory = factory)
    }
}