package ru.bahu.mypict.di

import dagger.Module
import dagger.Provides
import ru.bahu.mypict.datasource.DataSource
import ru.bahu.mypict.gson.Hits
import ru.bahu.mypict.retrofit.RetrofitImpl
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    internal fun provideDataSourceRemote(): DataSource<Hits> = RetrofitImpl()
}