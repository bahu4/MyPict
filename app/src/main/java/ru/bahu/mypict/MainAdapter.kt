package ru.bahu.mypict

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.bahu.mypict.data.Data
import ru.bahu.mypict.data.DataList

class MainAdapter(private var onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<MainAdapter.RecyclerItemViewHolder>() {
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

    inner class RecyclerItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var tv = view.findViewById<TextView>(R.id.text_header)
        fun bind(data: Data) {
            tv.text = data.title
            tv.setOnClickListener { showToast(data) }
        }
    }

    private fun showToast(data: Data) {
        onItemClickListener.onItemClick(data)
    }

    interface OnItemClickListener {
        fun onItemClick(data: Data)
    }
}
