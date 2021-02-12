package ru.bahu.mypict.retrofit


import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.bahu.mypict.datasource.DataSource
import ru.bahu.mypict.gson.Hits

class RetrofitImpl : DataSource<Hits> {
    override fun getDataFromDataSource(): Observable<Hits> {
        return getService().getPicture(API_KEY)
    }

    private fun getService(): ApiService {
        return createRetrofit().create(ApiService::class.java)
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(createGsonFactory())
            .build()
    }

    private fun createGsonFactory(): GsonConverterFactory {
        val gson: Gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
        return GsonConverterFactory.create(gson)
    }

    companion object {
        private const val BASE_URL = "https://pixabay.com"
        private const val API_KEY = "17939272-018aff6c2ff6a3825dba0892b"
    }
}

