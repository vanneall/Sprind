package ru.point.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface RequestDao {

    @Query("select * from requests")
    fun getAll(): Single<List<RequestEntity>>

    @Insert
    fun insertRequestEntity(requestEntity: RequestEntity): Completable

    @Delete
    fun delete(requestEntity: RequestEntity)

}