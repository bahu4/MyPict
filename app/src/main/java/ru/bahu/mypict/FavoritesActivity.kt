package ru.bahu.mypict

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.bahu.mypict.app.App
import ru.bahu.mypict.glide.GlideLoader
import ru.bahu.mypict.gson.TopPicture
import ru.bahu.mypict.room.FavoritesEntity
import javax.inject.Inject

class FavoritesActivity : MvpAppCompatActivity(), FavoritesView {
    @Inject
    lateinit var glideLoader: GlideLoader

    @Inject
    @InjectPresenter
    lateinit var favoritesPresenter: FavoritesPresenter

    @ProvidePresenter
    fun provideFavoritesPresenter() = favoritesPresenter

    private val onFavoriteItemClickListener: FavoritesAdapter.OnFavoriteItemClickListener =
        object : FavoritesAdapter.OnFavoriteItemClickListener {
            override fun onFavoriteItemClick(picture: FavoritesEntity?) {
                val intent = Intent(this@FavoritesActivity, DetailActivity::class.java)
                intent.putExtra("URL", picture?.url)
                startActivity(intent)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)
    }

    override fun renderFavoritesData(data: List<FavoritesEntity>?) {
        val favoritesRecyclerView = findViewById<RecyclerView>(R.id.favorites_recycler_view)
        val favoritesAdapter = FavoritesAdapter(glideLoader, onFavoriteItemClickListener, data)
        favoritesRecyclerView.layoutManager = GridLayoutManager(this, 2)
        favoritesRecyclerView.adapter = favoritesAdapter
    }
}