package ru.bahu.mypict

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import ru.bahu.mypict.glide.GlideLoader
import ru.bahu.mypict.gson.TopPicture

class FavoritesAdapter(
    private var glideLoader: GlideLoader,
    private var picture: List<TopPicture>
) : RecyclerView.Adapter<FavoritesAdapter.RecyclerFavoriteItemHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerFavoriteItemHolder =
        RecyclerFavoriteItemHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.favorite_item, parent, false)
        )

    override fun onBindViewHolder(holder: RecyclerFavoriteItemHolder, position: Int) {
        holder.bind(picture[position])
    }

    override fun getItemCount(): Int = picture.size

    inner class RecyclerFavoriteItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var favoritesItem = view.findViewById<ImageView>(R.id.favorite_item)
        fun bind(data: TopPicture) {
            glideLoader.loadPicture(itemView.context, data.webformatURL, favoritesItem)
        }
    }
}