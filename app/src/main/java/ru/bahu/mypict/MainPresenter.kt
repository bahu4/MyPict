package ru.bahu.mypict

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.bahu.mypict.gson.Hits
import ru.bahu.mypict.gson.TopPicture
import ru.bahu.mypict.repository.RepositoryImpl
import ru.bahu.mypict.room.FavoritesDao
import ru.bahu.mypict.room.FavoritesEntity
import javax.inject.Inject

@InjectViewState
class MainPresenter @Inject constructor(private val repositoryImpl: RepositoryImpl) :
    MvpPresenter<MainView>() {

    @Inject
    lateinit var favoritesDao: FavoritesDao

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

    fun addToDB(picture: TopPicture) {
        val disposable: Disposable = favoritesDao
            .insert(FavoritesEntity(picture.webformatURL))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }
}