package ru.bahu.mypict.ui.main

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.bahu.mypict.gson.TopPicture

interface MainView : MvpView {
    @StateStrategyType(value = AddToEndStrategy::class)
    fun renderData(data: List<TopPicture>)
}