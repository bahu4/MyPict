package ru.bahu.mypict

import ru.bahu.mypict.gson.TopPicture

interface MainView {
       fun renderData(data: List<TopPicture>)
}