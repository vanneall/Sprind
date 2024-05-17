package ru.point.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.point.room.RequestDatabase
import javax.inject.Singleton

@Module(includes = [DaoModule::class])
class RoomModule {

    @Provides
    @Singleton
    fun providesRoomDatabase(context: Context): RequestDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = RequestDatabase::class.java,
            name = "request_database"
        ).build()
    }
}