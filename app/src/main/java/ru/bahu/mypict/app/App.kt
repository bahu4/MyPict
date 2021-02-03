package ru.bahu.mypict.app

import android.app.Application
import androidx.room.Room
import ru.bahu.mypict.di.AppComponent
import ru.bahu.mypict.di.DaggerAppComponent
import ru.bahu.mypict.room.FavoritesDataBase

class App : Application() {
    companion object {
        lateinit var appComponent: AppComponent
        lateinit var appDatabase: FavoritesDataBase
    }

    override fun onCreate() {
        super.onCreate()
        initDagger()
        initDataBase()
    }

    private fun initDataBase() {
        appDatabase =
            Room
                .databaseBuilder(
                    applicationContext,
                    FavoritesDataBase::class.java,
                    "favorite_base"
                )
                .build()
    }

    private fun initDagger() {
        appComponent = DaggerAppComponent.builder()
            .build()
    }
}