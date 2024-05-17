package ru.point.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [RequestEntity::class]
)
abstract class RequestDatabase: RoomDatabase() {

    abstract fun provideRequestDao(): RequestDao

}