package ru.bahu.mypict.repository

import io.reactivex.Observable

interface Repository<T> {
    fun getDataFromRepository(): Observable<T>
}