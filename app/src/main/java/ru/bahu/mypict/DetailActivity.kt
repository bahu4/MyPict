package ru.bahu.mypict

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import ru.bahu.mypict.app.App
import ru.bahu.mypict.glide.GlideLoader
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {
    @Inject
    lateinit var glideLoader: GlideLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val detailImageView = findViewById<ImageView>(R.id.detail_image_view)
        glideLoader.loadPicture(
            this@DetailActivity,
            intent.extras?.getString("URL"),
            detailImageView
        )
    }
}