package ru.bahu.mypict.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import ru.bahu.mypict.gson.Hits

interface ApiService {
    @GET("api")
    fun getPicture(@Query("key") key: String): Observable<Hits>

    companion object Factory {
        private const val BASE_URL = "https://pixabay.com"
        private const val API_KEY = "17939272-018aff6c2ff6a3825dba0892b"
        fun requestServer(): Observable<Hits> {
            val gson: Gson = GsonBuilder().create()
            val gsonConverterFactory: GsonConverterFactory = GsonConverterFactory.create(gson)
            val api: ApiService = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(gsonConverterFactory)
                .baseUrl(BASE_URL)
                .build()
                .create(ApiService::class.java)
            return api.getPicture(API_KEY).subscribeOn(Schedulers.io())
        }
    }
}