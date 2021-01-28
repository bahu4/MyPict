package ru.bahu.mypict.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_pictures")
class FavoritesEntity(
    @field:PrimaryKey(autoGenerate = true)
    @field:ColumnInfo(name = "id") var id: Int,
    @field:ColumnInfo(name = "url") var url: String
)