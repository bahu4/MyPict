package ru.bahu.mypict.datasource

import io.reactivex.Observable

interface DataSource<T> {
    fun getDataFromDataSource(): Observable<T>
}