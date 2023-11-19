package com.ikapurwanti.movie.data.network.api.service

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.ikapurwanti.movie.BuildConfig
import com.ikapurwanti.movie.data.network.api.model.Category
import com.ikapurwanti.movie.data.network.api.model.MovieResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface MovieService {
    @GET("dummy_videos.json")
    suspend fun getMovies(): MovieResponse

    companion object {
        @JvmStatic
        operator fun invoke(chucker: ChuckerInterceptor): MovieService {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(chucker)
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            return retrofit.create(MovieService::class.java)
        }
    }
}