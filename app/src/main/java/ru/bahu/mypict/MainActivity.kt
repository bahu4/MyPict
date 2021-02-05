package ru.bahu.mypict

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.bahu.mypict.app.App
import ru.bahu.mypict.glide.GlideLoader
import ru.bahu.mypict.gson.TopPicture
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainView {

    @Inject
    lateinit var glideLoader: GlideLoader

    @Inject
    @InjectPresenter
    lateinit var mainPresenter: MainPresenter

    @ProvidePresenter
    fun providePresenter(): MainPresenter = mainPresenter

    private val onItemClickListener: MainAdapter.OnItemClickListener =
        object : MainAdapter.OnItemClickListener {
            override fun onItemClick(picture: TopPicture) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra("URL", picture.webformatURL)
                startActivity(intent)
            }

            override fun addToFavoritesClick(picture: TopPicture) {
                mainPresenter.addToDB(picture)
                Toast.makeText(
                    this@MainActivity,
                    getString(R.string.added_to_favorites_toast_text),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var btn = findViewById<Button>(R.id.btn)
        btn.setOnClickListener {
            val intent = Intent(this, FavoritesActivity::class.java)
            startActivity(intent)
        }
    }

    override fun renderData(data: List<TopPicture>) {
        val mainRV = findViewById<RecyclerView>(R.id.main_rv)
        val adapter = MainAdapter(glideLoader, onItemClickListener, data)
        mainRV.adapter = adapter
        mainRV.layoutManager = GridLayoutManager(this, 2)
    }
}