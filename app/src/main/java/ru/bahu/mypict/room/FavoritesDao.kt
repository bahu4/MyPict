package ru.bahu.mypict.room

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface FavoritesDao {
    @Query("SELECT * FROM favorite_pictures")
    fun getAllFromDB(): Single<List<FavoritesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: FavoritesEntity): Completable

    @Delete
    fun deleteElementFromDB(entity: FavoritesEntity?): Completable
}