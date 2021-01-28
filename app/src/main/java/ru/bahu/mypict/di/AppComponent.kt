package ru.bahu.mypict.di

import dagger.Component
import ru.bahu.mypict.DescriptionActivity
import ru.bahu.mypict.MainActivity
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
    fun inject(activity: DescriptionActivity)
}