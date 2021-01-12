package ru.bahu.mypict

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.bahu.mypict.data.Data
import ru.bahu.mypict.data.DataList

class MainAdapter : RecyclerView.Adapter<MainAdapter.RecyclerItemViewHolder>() {
    var list = DataList.getList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerItemViewHolder = RecyclerItemViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)
    )

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    class RecyclerItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv = itemView.findViewById<TextView>(R.id.text_header)
        fun bind(data: Data) {
            tv.text = data.title
        }
    }
}
