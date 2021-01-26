package ru.bahu.mypict

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.bahu.mypict.gson.Hits
import ru.bahu.mypict.repository.RepositoryImpl
import javax.inject.Inject

@InjectViewState
class MainPresenter @Inject constructor(private val repositoryImpl: RepositoryImpl) :
    MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getPictureList()
    }

    private fun getPictureList() {
        val single: Observable<Hits> = repositoryImpl.getDataFromRepository()
        val disposable: Disposable = single
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.renderData(it.hits)
            }, {
                it.printStackTrace()
            })
    }
}