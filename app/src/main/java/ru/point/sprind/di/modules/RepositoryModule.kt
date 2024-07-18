package ru.point.sprind.di.modules

import dagger.Module
import dagger.Provides
import ru.point.domain.paging.CartPagingSource
import ru.point.domain.paging.FavoritePagingSource
import ru.point.domain.repository.CartRepository
import ru.point.domain.repository.CategoryRepository
import ru.point.domain.repository.FavoriteRepository
import ru.point.domain.repository.ProductRepository
import ru.point.domain.repository.RequestRepository
import ru.point.domain.repository.ReviewRepository
import ru.point.domain.repository.ShopRepository
import ru.point.domain.repository.UserRepository
import ru.point.repository.local.RequestRepositoryImpl
import ru.point.repository.remote.CategoryRepositoryImpl
import ru.point.repository.remote.RemoteCartRepository
import ru.point.repository.remote.RemoteFavoriteRepository
import ru.point.repository.remote.RemoteProductRepository
import ru.point.repository.remote.RemoteReviewRepository
import ru.point.repository.remote.RemoteUserRepository
import ru.point.repository.remote.ShopRepositoryImpl
import ru.point.retrofit.api.CartApi
import ru.point.retrofit.api.CategoryApi
import ru.point.retrofit.api.FavoriteApi
import ru.point.retrofit.api.ProductApi
import ru.point.retrofit.api.ReviewApi
import ru.point.retrofit.api.ShopApi
import ru.point.retrofit.api.UserApi
import ru.point.room.RequestDao

@Module(includes = [ApiModule::class, PagingSourceModule::class])
class RepositoryModule {
    @Provides
    fun provideRemoteProductRepository(
        productApi: ProductApi,
        categoryApi: CategoryApi
    ): ProductRepository {
        return RemoteProductRepository(productApi = productApi, categoriesApi = categoryApi)
    }

    @Provides
    fun provideRemoteCartRepository(api: CartApi): CartRepository {
        return RemoteCartRepository(api = api)
    }

    @Provides
    fun provideRemoteFavoriteRepository(api: FavoriteApi): FavoriteRepository {
        return RemoteFavoriteRepository(api = api)
    }

    @Provides
    fun provideUserRepository(api: UserApi): UserRepository {
        return RemoteUserRepository(api = api)
    }

    @Provides
    fun provideReviewRepository(api: ReviewApi): ReviewRepository {
        return RemoteReviewRepository(api = api)
    }

    @Provides
    fun provideRequestRepository(dao: RequestDao): RequestRepository {
        return RequestRepositoryImpl(dao = dao)
    }

    @Provides
    fun provideCategoryRepository(api: CategoryApi): CategoryRepository {
        return CategoryRepositoryImpl(api = api)
    }

    @Provides
    fun provideShopRepository(api: ShopApi): ShopRepository {
        return ShopRepositoryImpl(api = api)
    }
}

@Module
class PagingSourceModule {
    @Provides
    fun provideFavoritePagingSource(repository: FavoriteRepository): FavoritePagingSource {
        return FavoritePagingSource(repository = repository)
    }

    @Provides
    fun provideCartPagingSource(repository: CartRepository): CartPagingSource {
        return CartPagingSource(repository = repository)
    }


}