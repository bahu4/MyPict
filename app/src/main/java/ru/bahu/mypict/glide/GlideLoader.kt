package ru.bahu.mypict.glide

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

class GlideLoader {
    fun loadPicture(context: Context, url: String?, view: ImageView) {
        Glide
            .with(context)
            .load(url)
            .into(view)
    }
}