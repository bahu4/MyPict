package ru.bahu.mypict.datasource

import io.reactivex.Observable
import ru.bahu.mypict.gson.Hits
import ru.bahu.mypict.retrofit.RetrofitImpl

class DataSourceRemote (private val remoteProvider: RetrofitImpl = RetrofitImpl()) :
    DataSource<Hits> {
    override fun getDataFromDataSource(): Observable<Hits> = remoteProvider.getDataFromDataSource()
}