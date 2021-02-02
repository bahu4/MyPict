package ru.bahu.mypict

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

import ru.bahu.mypict.glide.GlideLoader
import ru.bahu.mypict.gson.TopPicture

class MainAdapter(
    private var glideLoader: GlideLoader,
    private var onItemClickListener: OnItemClickListener,
    private var pictures: List<TopPicture>
) :
    RecyclerView.Adapter<MainAdapter.RecyclerItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerItemViewHolder = RecyclerItemViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)
    )

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(pictures[position])
    }

    override fun getItemCount() = pictures.size

    inner class RecyclerItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var picturesView = view.findViewById<ImageView>(R.id.picture_item)
        private var addToDBCheckBox = view.findViewById<CheckBox>(R.id.add_to_db_box)

        fun bind(data: TopPicture) {
            glideLoader.loadPicture(itemView.context, data.webformatURL, picturesView)
            picturesView.setOnClickListener { startDescriptionActivity(data) }
            addToDBCheckBox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    addPictureToFavorites(data)
                }
            }
        }
    }

    private fun startDescriptionActivity(picture: TopPicture) {
        onItemClickListener.onItemClick(picture)
    }

    private fun addPictureToFavorites(picture: TopPicture) {
        onItemClickListener.addToFavoritesClick(picture)
    }

    interface OnItemClickListener {
        fun onItemClick(picture: TopPicture)
        fun addToFavoritesClick(picture: TopPicture)
    }
}
