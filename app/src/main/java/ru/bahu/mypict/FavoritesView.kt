package ru.bahu.mypict

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.bahu.mypict.gson.TopPicture

interface FavoritesView: MvpView {
    @StateStrategyType(value = AddToEndStrategy::class)
    fun renderFavoritesData(data: List<TopPicture>)
}