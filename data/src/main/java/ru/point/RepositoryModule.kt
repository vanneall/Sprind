package ru.point

import dagger.Module
import dagger.Provides
import ru.point.data.FakeRepository
import ru.point.domain.repository.ProductRepository

@Module
class RepositoryModule {
    @Provides
    fun provideProductRepository(): ProductRepository {
        return FakeRepository()
    }
}