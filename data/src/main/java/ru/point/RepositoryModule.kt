package ru.point

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.point.domain.repository.ProductRepository
import ru.point.repository.retrofit.ProductApi
import ru.point.repository.retrofitRepository.RemoteProductRepository

@Module
class RepositoryModule {
    @Provides
    fun provideRemoteProductRepository(api: ProductApi): ProductRepository {
        return RemoteProductRepository(api = api)
    }

    @Provides
    fun provideProductApi(retrofit: Retrofit): ProductApi {
        return retrofit.create(ProductApi::class.java)
    }

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }


    companion object {
        const val BASE_URL = "http://192.168.0.192:8080/sprind/"
    }
}