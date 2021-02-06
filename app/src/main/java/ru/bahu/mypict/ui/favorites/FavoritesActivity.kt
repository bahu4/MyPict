package ru.bahu.mypict.ui.favorites

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.bahu.mypict.R
import ru.bahu.mypict.app.App
import ru.bahu.mypict.glide.GlideLoader
import ru.bahu.mypict.presenter.FavoritesPresenter
import ru.bahu.mypict.room.FavoritesEntity
import ru.bahu.mypict.ui.detail.DetailActivity
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

            override fun deleteItemFromFavoriteClick(picture: FavoritesEntity?) {
                favoritesPresenter.deletePicturesFromDataBase(picture)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)
        initToolbar()
    }

    private fun initToolbar() {
        val myToolbar = findViewById<Toolbar>(R.id.my_toolbar)
        setSupportActionBar(myToolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        myToolbar.title = getString(R.string.favorite_toolbar_title)
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onSupportNavigateUp()
        finish()
        return true
    }

    override fun renderFavoritesData(data: List<FavoritesEntity>?) {
        val favoritesRecyclerView = findViewById<RecyclerView>(R.id.favorites_recycler_view)
        val favoritesAdapter = FavoritesAdapter(glideLoader, onFavoriteItemClickListener, data)
        favoritesRecyclerView.layoutManager = GridLayoutManager(this, 2)
        favoritesRecyclerView.adapter = favoritesAdapter
    }
}