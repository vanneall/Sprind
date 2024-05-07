package ru.point.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.point.domain.repository.CartRepository
import ru.point.domain.repository.FavoriteRepository
import ru.point.domain.repository.ProductRepository
import ru.point.domain.repository.UserRepository
import ru.point.repository.retrofit.AuthorizationInterceptor
import ru.point.repository.retrofit.CartApi
import ru.point.repository.retrofit.FavoriteApi
import ru.point.repository.retrofit.ProductApi
import ru.point.repository.retrofit.UserApi
import ru.point.repository.retrofitRepository.RemoteCartRepository
import ru.point.repository.retrofitRepository.RemoteFavoriteRepository
import ru.point.repository.retrofitRepository.RemoteProductRepository
import ru.point.repository.retrofitRepository.RemoteUserRepository
import javax.inject.Singleton

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



}