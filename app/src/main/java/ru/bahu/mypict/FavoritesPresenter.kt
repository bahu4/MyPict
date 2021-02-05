package ru.bahu.mypict

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.bahu.mypict.room.FavoritesDao
import javax.inject.Inject

@InjectViewState
class FavoritesPresenter @Inject constructor(private val favoritesDao: FavoritesDao) :
    MvpPresenter<FavoritesView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getPicturesFromDataBase()
    }

    private fun getPicturesFromDataBase() {
        var disposable: Disposable = favoritesDao.getAllFromDB().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({
                viewState.renderFavoritesData(it)
            }, {
                it.printStackTrace()
            }
            )
    }
}