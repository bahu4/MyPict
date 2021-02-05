package ru.bahu.mypict

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import ru.bahu.mypict.glide.GlideLoader
import ru.bahu.mypict.gson.TopPicture
import ru.bahu.mypict.room.FavoritesEntity

class FavoritesAdapter(
    private var glideLoader: GlideLoader,
    private var onFavoriteItemClickListener: OnFavoriteItemClickListener,
    private var picture: List<FavoritesEntity>?
) : RecyclerView.Adapter<FavoritesAdapter.RecyclerFavoriteItemHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerFavoriteItemHolder =
        RecyclerFavoriteItemHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.favorite_item, parent, false)
        )

    override fun onBindViewHolder(holder: RecyclerFavoriteItemHolder, position: Int) {
        holder.bind(picture?.get(position))
    }

    override fun getItemCount(): Int = picture?.size ?: 0

    inner class RecyclerFavoriteItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var favoritesItem = view.findViewById<ImageView>(R.id.favorite_item)
        fun bind(data: FavoritesEntity?) {
            glideLoader.loadPicture(itemView.context, data?.url, favoritesItem)
            favoritesItem.setOnClickListener { startDescriptionActivityFromFavoriteActivity(data) }
            favoritesItem.setOnLongClickListener { deleteItemFromDataBase(data) }
        }
    }

    private fun startDescriptionActivityFromFavoriteActivity(picture: FavoritesEntity?) {
        onFavoriteItemClickListener.onFavoriteItemClick(picture)
    }

    private fun deleteItemFromDataBase(picture: FavoritesEntity?): Boolean {
        onFavoriteItemClickListener.deleteItemFromFavoriteClick(picture)
        return true
    }

    interface OnFavoriteItemClickListener {
        fun onFavoriteItemClick(picture: FavoritesEntity?)
        fun deleteItemFromFavoriteClick(picture: FavoritesEntity?)
    }
}