package ru.bahu.mypict.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.bahu.mypict.gson.Hits

class RetrofitService {
    fun requestServer(): Observable<Hits> {
        val gson: Gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
        val gsonConverterFactory: GsonConverterFactory = GsonConverterFactory.create(gson)
        val api: ApiService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(ApiService::class.java)
        return api.getPicture(API_KEY).subscribeOn(Schedulers.io())
    }

    companion object {
        private const val BASE_URL = "https://pixabay.com"
        private const val API_KEY = "17939272-018aff6c2ff6a3825dba0892b"
    }
}