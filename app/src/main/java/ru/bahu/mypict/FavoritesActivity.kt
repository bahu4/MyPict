package ru.bahu.mypict

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.bahu.mypict.app.App
import ru.bahu.mypict.glide.GlideLoader
import ru.bahu.mypict.gson.TopPicture
import javax.inject.Inject

class FavoritesActivity : MvpAppCompatActivity(), FavoritesView {
    @Inject
    lateinit var glideLoader: GlideLoader

    @Inject
    @InjectPresenter
    lateinit var favoritesPresenter: FavoritesPresenter

    @ProvidePresenter
    fun provideFavoritesPresenter() = favoritesPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)
    }

    override fun renderFavoritesData(data: List<TopPicture>) {
        val favoritesRecyclerView = findViewById<RecyclerView>(R.id.favorites_recycler_view)
        val favoritesAdapter = FavoritesAdapter(glideLoader, data)
        favoritesRecyclerView.layoutManager = GridLayoutManager(this, 2)
        favoritesRecyclerView.adapter = favoritesAdapter
    }
}