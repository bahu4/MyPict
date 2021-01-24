package ru.bahu.mypict

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.bahu.mypict.gson.Hits
import ru.bahu.mypict.retrofit.ApiService

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {
    fun getPictureList() {
        val single: Observable<Hits> = ApiService.requestServer()
        val disposable: Disposable = single
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.renderData(it.hits)
            }, {
                it.printStackTrace()
            })
    }
}