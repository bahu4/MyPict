package ru.bahu.mypict.app

import android.app.Application
import ru.bahu.mypict.di.AppComponent
import ru.bahu.mypict.di.DaggerAppComponent

class App : Application() {
    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        appComponent = DaggerAppComponent.builder()
            .build()
    }
}