package ru.bahu.mypict

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import ru.bahu.mypict.app.App
import ru.bahu.mypict.glide.GlideLoader
import javax.inject.Inject

class DescriptionActivity : AppCompatActivity() {
    @Inject
    lateinit var glideLoader: GlideLoader
    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)
        init()
    }

    private fun init() {
        val descriptionImageView = findViewById<ImageView>(R.id.description_image_view)
        val bundle = intent.extras
        bundle?.getString("URL")?.let { glideLoader.loadPicture(this, it, descriptionImageView) }

    }
}