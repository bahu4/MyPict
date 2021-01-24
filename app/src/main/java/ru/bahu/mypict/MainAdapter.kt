package ru.bahu.mypict

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

import ru.bahu.mypict.glide.GlideLoader
import ru.bahu.mypict.gson.TopPicture

class MainAdapter(
    private var onItemClickListener: OnItemClickListener,
    var pictures: List<TopPicture>
) :
    RecyclerView.Adapter<MainAdapter.RecyclerItemViewHolder>() {
    var glideLoader = GlideLoader()

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

        fun bind(data: TopPicture) {
            glideLoader.loadPicture(itemView.context, data.webformatURL, picturesView)
            itemView.setOnClickListener { showToast(data) }
        }
    }

    private fun showToast(picture: TopPicture) {
        onItemClickListener.onItemClick(picture)
    }

    interface OnItemClickListener {
        fun onItemClick(picture: TopPicture)
    }
}
