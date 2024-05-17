package ru.point.di

import dagger.Module
import dagger.Provides
import ru.point.room.RequestDao
import ru.point.room.RequestDatabase

@Module
class DaoModule {

    @Provides
    fun provideRequestDao(database: RequestDatabase): RequestDao {
        return database.provideRequestDao()
    }
}