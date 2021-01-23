package ru.bahu.mypict

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.bahu.mypict.gson.TopPicture

class MainActivity : AppCompatActivity(), MainView {

//    private val onItemClickListener: MainAdapter.OnItemClickListener =
//        object : MainAdapter.OnItemClickListener {
//            override fun onItemClick(data: Data) {
//                Toast.makeText(this@MainActivity, data.title, Toast.LENGTH_SHORT).show()
//            }
//        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mainPresenter = MainPresenter(this)
        mainPresenter.getPictureList()
    }

    override fun renderData(data: List<TopPicture>) {

        val mainRV = findViewById<RecyclerView>(R.id.main_rv)
        val adapter = MainAdapter(this, data)
        mainRV.adapter = adapter
        mainRV.layoutManager = GridLayoutManager(this, 2)

    }
}