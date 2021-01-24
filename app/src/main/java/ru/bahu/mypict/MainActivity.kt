package ru.bahu.mypict

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.bahu.mypict.gson.TopPicture

class MainActivity : MvpAppCompatActivity(), MainView {

    @InjectPresenter
    lateinit var mainPresenter: MainPresenter

    private val onItemClickListener: MainAdapter.OnItemClickListener =
        object : MainAdapter.OnItemClickListener {
            override fun onItemClick(picture: TopPicture) {
                Toast.makeText(this@MainActivity, picture.webformatURL, Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainPresenter.getPictureList()
    }

    override fun renderData(data: List<TopPicture>) {
        val mainRV = findViewById<RecyclerView>(R.id.main_rv)
        val adapter = MainAdapter(onItemClickListener, this, data)
        mainRV.adapter = adapter
        mainRV.layoutManager = GridLayoutManager(this, 2)
    }
}