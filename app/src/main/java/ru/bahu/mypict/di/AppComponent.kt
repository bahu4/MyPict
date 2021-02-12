package ru.bahu.mypict.di

import dagger.Component
import ru.bahu.mypict.ui.detail.DetailActivity
import ru.bahu.mypict.ui.favorites.FavoritesActivity
import ru.bahu.mypict.ui.main.MainActivity
import javax.inject.Singleton

@Component(
    modules = [
        RepositoryModule::class,
        ImageLoadingModule::class
    ]
)
@Singleton
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(activity: DetailActivity)
    fun inject(activity: FavoritesActivity)
}