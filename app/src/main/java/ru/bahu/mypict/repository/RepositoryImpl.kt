package ru.bahu.mypict.repository

import io.reactivex.Observable
import ru.bahu.mypict.datasource.DataSource
import ru.bahu.mypict.gson.Hits
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val dataSource: DataSource<Hits>) :
    Repository<Hits> {
    override fun getDataFromRepository(): Observable<Hits> {
        return dataSource.getDataFromDataSource()
    }
}