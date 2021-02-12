package ru.bahu.mypict.di

import dagger.Module
import dagger.Provides
import ru.bahu.mypict.glide.GlideLoader
import javax.inject.Singleton

@Module
class ImageLoadingModule {
    @Provides
    @Singleton
    internal fun provideGlideLoader(): GlideLoader = GlideLoader()
}