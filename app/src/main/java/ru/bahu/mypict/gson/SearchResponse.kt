package ru.bahu.mypict.gson

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TopPicture(
    @Expose
    @field:SerializedName("webformatURL") val webformatURL: String
)

data class Hits(
    @Expose
    @field:SerializedName("hits") val hits: List<TopPicture>
)