package ru.point.di

import dagger.Module
import dagger.Provides
import ru.point.domain.repository.CartRepository
import ru.point.domain.repository.FavoriteRepository
import ru.point.domain.repository.ProductRepository
import ru.point.domain.repository.ReviewRepository
import ru.point.domain.repository.UserRepository
import ru.point.retrofit.api.CartApi
import ru.point.retrofit.api.FavoriteApi
import ru.point.retrofit.api.ProductApi
import ru.point.retrofit.api.ReviewApi
import ru.point.retrofit.api.UserApi
import ru.point.repository.remote.RemoteCartRepository
import ru.point.repository.remote.RemoteFavoriteRepository
import ru.point.repository.remote.RemoteProductRepository
import ru.point.repository.remote.RemoteReviewRepository
import ru.point.repository.remote.RemoteUserRepository

@Module(includes = [ApiModule::class])
class RepositoryModule {
    @Provides
    fun provideRemoteProductRepository(api: ProductApi): ProductRepository {
        return RemoteProductRepository(api = api)
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
}