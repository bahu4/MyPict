package ru.bahu.mypict

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import ru.bahu.mypict.gson.Hits
import ru.bahu.mypict.retrofit.ApiService

class MainPresenter(var view: MainView) {
    fun getPictureList() {
        val single: Observable<Hits> = ApiService.requestServer()
        val disposable: Disposable = single
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.renderData(it.hits)
            }, {
                it.printStackTrace()
            })
    }
}