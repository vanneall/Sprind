package ru.point.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.point.repository.retrofit.CartApi
import ru.point.repository.retrofit.FavoriteApi
import ru.point.repository.retrofit.ProductApi

@Module
class ApiModule {
    @Provides
    fun provideCartApi(retrofit: Retrofit): CartApi {
        return retrofit.create(CartApi::class.java)
    }

    @Provides
    fun provideProductApi(retrofit: Retrofit): ProductApi {
        return retrofit.create(ProductApi::class.java)
    }

    @Provides
    fun provideFavoriteApi(retrofit: Retrofit): FavoriteApi {
        return retrofit.create(FavoriteApi::class.java)
    }
}