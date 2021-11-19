package com.ck.api

import com.ck.data.*
import kotlinx.coroutines.flow.Flow
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


/**
 *
 * @author ck
 * @date 2020/12/14
 */
interface ApiService {

    /**
     * 首页轮播/资讯
     */
    @FormUrlEncoded
    @POST("informationAct/list.html")
    suspend fun getNewsList(@FieldMap options: Map<String, String>): HomeResponse

    /**
     * 限时折扣，热门推销
     */
    @FormUrlEncoded
    @POST("carAct/list.html")
    suspend fun getCarList(@FieldMap options: Map<String, String>): CarResponse

    /**
     * 消息中心
     */
    @FormUrlEncoded
    @POST("messageAct/list.html")
    suspend fun getMsgList(@FieldMap options: Map<String, String>): MsgResponse

    /**
     * 资讯中心
     */
    @FormUrlEncoded
    @POST("informationAct/list.html")
    suspend fun getHomeNews(
        @FieldMap options: Map<String, String>
    ): NewsResponse

    /**
     * 提交托管
     */
    @FormUrlEncoded
    @POST("carTrusteeshipAct/addCarTrusteeship.html")
    suspend fun submitCarHelp(
        @FieldMap options: Map<String, String>
    ): BaseResponse

    /**
     * 车辆详情
     */
    @FormUrlEncoded
    @POST("carAct/findById.html")
    suspend fun getCarDetail(
        @FieldMap options: Map<String, String>
    ): CarDetailResponse

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("userAct/userLogin.html")
    suspend fun login(
        @FieldMap options: Map<String, String>
    ): LoginResponse


    /**
     * 测试用
     */
    @GET("informationAct/list.html")
    fun getHomeData(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): HomeResponse

    companion object {
        private const val BASE_URL = "https://java.xwfcx.com/newWfcx/"

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