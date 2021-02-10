package ru.bahu.mypict.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.bahu.mypict.presenter.MainPresenter
import ru.bahu.mypict.R
import ru.bahu.mypict.app.App
import ru.bahu.mypict.glide.GlideLoader
import ru.bahu.mypict.gson.TopPicture
import ru.bahu.mypict.ui.detail.DetailActivity
import ru.bahu.mypict.ui.favorites.FavoritesActivity
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
        initFab()
    }

    private fun initFab() {
        val fab = findViewById<ExtendedFloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
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