package com.ck.api

import com.ck.data.HomeResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


/**
 *
 * @author ck
 * @date 2020/12/14
 */
interface ApiService {

    @GET("newWfcx/informationAct/list.html")
    suspend fun getHomeData(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): HomeResponse

    companion object {
        private const val BASE_URL = "https://java.xwfcx.com/"

        fun create(): ApiService {
            val logger = HttpLoggingInterceptor().apply {
                level =
                    HttpLoggingInterceptor.Level.BASIC
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}