package ru.bahu.mypict.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Single

@Dao
interface FavoritesDao {
    @Query("SELECT * FROM favorite_pictures")
    fun selectAllFromDB(): Single<List<FavoritesEntity>>

    @Insert
    fun insert(entity: FavoritesEntity)
}