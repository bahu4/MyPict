package ru.bahu.mypict.gson

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Hits {
    @Expose
    @SerializedName("hits")
    val topImagesList: List<TopImage>? = null
}