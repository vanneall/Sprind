package ru.point.sprind.di.modules

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.point.retrofit.api.CartApi
import ru.point.retrofit.api.CategoryApi
import ru.point.retrofit.api.FavoriteApi
import ru.point.retrofit.api.ProductApi
import ru.point.retrofit.api.ReviewApi
import ru.point.retrofit.api.ShopApi
import ru.point.retrofit.api.UserApi

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

    @Provides
    fun provideUserApi(retrofit: Retrofit): UserApi {
        return retrofit.create(UserApi::class.java)
    }

    @Provides
    fun provideReviewApi(retrofit: Retrofit): ReviewApi {
        return retrofit.create(ReviewApi::class.java)
    }

    @Provides
    fun provideCategoryApi(retrofit: Retrofit): CategoryApi {
        return retrofit.create(CategoryApi::class.java)
    }

    @Provides
    fun provideShopApi(retrofit: Retrofit): ShopApi {
        return retrofit.create(ShopApi::class.java)
    }
}