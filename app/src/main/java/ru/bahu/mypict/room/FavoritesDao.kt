package ru.bahu.mypict.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface FavoritesDao {
    @Query("SELECT * FROM favorite_pictures")
    fun getAllFromDB(): Single<List<FavoritesEntity>>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: FavoritesEntity): Completable
}