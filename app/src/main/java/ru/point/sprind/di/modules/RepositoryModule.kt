package ru.point.sprind.di.modules

import dagger.Module
import dagger.Provides
import ru.point.domain.repository.CartRepository
import ru.point.domain.repository.FavoriteRepository
import ru.point.domain.repository.ProductRepository
import ru.point.domain.repository.RequestRepository
import ru.point.domain.repository.ReviewRepository
import ru.point.domain.repository.UserRepository
import ru.point.repository.local.RequestRepositoryImpl
import ru.point.repository.paging.CartPagingSource
import ru.point.repository.paging.FavoritePagingSource
import ru.point.repository.paging.FeedPagingSource
import ru.point.repository.remote.RemoteCartRepository
import ru.point.repository.remote.RemoteFavoriteRepository
import ru.point.repository.remote.RemoteProductRepository
import ru.point.repository.remote.RemoteReviewRepository
import ru.point.repository.remote.RemoteUserRepository
import ru.point.retrofit.api.CartApi
import ru.point.retrofit.api.FavoriteApi
import ru.point.retrofit.api.ProductApi
import ru.point.retrofit.api.ReviewApi
import ru.point.retrofit.api.UserApi
import ru.point.room.RequestDao

@Module(includes = [ApiModule::class, PagingSourceModule::class])
class RepositoryModule {
    @Provides
    fun provideRemoteProductRepository(
        api: ProductApi,
        pagingSource: FeedPagingSource.Factory
    ): ProductRepository {
        return RemoteProductRepository(api = api, pagingSource)
    }

    @Provides
    fun provideRemoteCartRepository(
        api: CartApi,
        cartPagingSource: CartPagingSource
    ): CartRepository {
        return RemoteCartRepository(api = api, cartPagingSource = cartPagingSource)
    }

    @Provides
    fun provideRemoteFavoriteRepository(
        api: FavoriteApi,
        favoritePagingSource: FavoritePagingSource
    ): FavoriteRepository {
        return RemoteFavoriteRepository(api = api, favoritePagingSource = favoritePagingSource)
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
}

@Module
class PagingSourceModule {
    @Provides
    fun provideFavoritePagingSource(api: FavoriteApi): FavoritePagingSource {
        return FavoritePagingSource(api = api)
    }

    @Provides
    fun provideCartPagingSource(api: CartApi): CartPagingSource {
        return CartPagingSource(api = api)
    }
}