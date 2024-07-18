package ru.point.sprind.di.usecases

import dagger.Module
import dagger.Provides
import ru.point.domain.paging.FeedPagingSource
import ru.point.domain.paging.NamedProductsPagingSource
import ru.point.domain.repository.ProductRepository
import ru.point.domain.usecase.implementation.product.GetMainProductsPageInfoUseCaseImpl
import ru.point.domain.usecase.implementation.product.GetProductByIdUseCaseImpl
import ru.point.domain.usecase.implementation.product.GetProductUseCaseImpl
import ru.point.domain.usecase.implementation.product.GetProductsByNameUseCaseImpl
import ru.point.domain.usecase.interfaces.product.GetMainProductsPageInfoUseCase
import ru.point.domain.usecase.interfaces.product.GetProductByIdUseCase
import ru.point.domain.usecase.interfaces.product.GetProductsByNameUseCase
import ru.point.domain.usecase.interfaces.product.GetProductsUseCase

@Module
class ProductUseCaseModule {

    @Provides
    fun provideGetProductUseCase(pagingSource: FeedPagingSource): GetProductsUseCase {
        return GetProductUseCaseImpl(pagingSource = pagingSource)
    }

    @Provides
    fun provideGetProductByNameUseCase(pagingSourceFactory: NamedProductsPagingSource.Factory): GetProductsByNameUseCase {
        return GetProductsByNameUseCaseImpl(pagingSourceFactory = pagingSourceFactory)
    }

    @Provides
    fun provideGetProductByIdUseCase(repository: ProductRepository): GetProductByIdUseCase {
        return GetProductByIdUseCaseImpl(repository = repository)
    }

    @Provides
    fun provideGetMainProductsPageInfoUseCase(repository: ProductRepository): GetMainProductsPageInfoUseCase {
        return GetMainProductsPageInfoUseCaseImpl(repository)
    }
}