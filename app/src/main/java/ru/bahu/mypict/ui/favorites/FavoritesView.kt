package ru.bahu.mypict.ui.favorites

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.bahu.mypict.room.FavoritesEntity

interface FavoritesView : MvpView {
    @StateStrategyType(value = AddToEndStrategy::class)
    fun renderFavoritesData(data: List<FavoritesEntity>?)
}