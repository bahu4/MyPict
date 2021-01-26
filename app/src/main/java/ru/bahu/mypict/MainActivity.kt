package ru.bahu.mypict

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.bahu.mypict.app.App
import ru.bahu.mypict.gson.TopPicture
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainView {

    @Inject
    @InjectPresenter
    lateinit var mainPresenter: MainPresenter

    @ProvidePresenter
    fun providePresenter(): MainPresenter = mainPresenter

    private val onItemClickListener: MainAdapter.OnItemClickListener =
        object : MainAdapter.OnItemClickListener {
            override fun onItemClick(picture: TopPicture) {
                Toast.makeText(this@MainActivity, picture.webformatURL, Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun renderData(data: List<TopPicture>) {
        val mainRV = findViewById<RecyclerView>(R.id.main_rv)
        val adapter = MainAdapter(onItemClickListener, data)
        mainRV.adapter = adapter
        mainRV.layoutManager = GridLayoutManager(this, 2)
    }
}