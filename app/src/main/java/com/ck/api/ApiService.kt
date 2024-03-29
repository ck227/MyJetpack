package com.ck.api

import com.ck.data.*
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
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
     * 注册获取验证码
     */
    @FormUrlEncoded
    @POST("userAct/sendPhoneCode.html")
    suspend fun getCode(
        @FieldMap options: Map<String, String>
    ): BaseResponse


    /**
     * 注册
     */
    @FormUrlEncoded
    @POST("userAct/register.html")
    suspend fun register(
        @FieldMap options: Map<String, String>
    ): BaseResponse


    /**
     * 找回密码
     */
    @FormUrlEncoded
    @POST("userAct/updatePwd.html")
    suspend fun findPwd(
        @FieldMap options: Map<String, String>
    ): BaseResponse

    /**
     * 上传图片
     */
    @Multipart
    @POST("fileUpload/fileUpload.html")
    suspend fun uploadPic(
        @Part("type") body: RequestBody,
        @Part file: MultipartBody.Part
    ): UploadPicResponse

    /**
     * 更新用户信息
     */
    @FormUrlEncoded
    @POST("userAct/updateUser.html")
    suspend fun updateUserInfo(
        @FieldMap options: Map<String, String>
    ): BaseResponse

    /**
     * 收藏/取消收藏
     */
    @FormUrlEncoded
    @POST("userCollectionAct/updateUserCollection.html")
    suspend fun updateCollect(
        @FieldMap options: Map<String, String>
    ): BaseResponse

    /**
     * 充值记录
     */
    @FormUrlEncoded
    @POST("userRechargeAct/list.html")
    suspend fun getChargeHistory(
        @FieldMap options: Map<String, String>
    ): ChargeHistoryResponse

    /**
     * 获取微信充值信息
     */
    @FormUrlEncoded
    @POST("userRechargeAct/wxRecharge.html")
    suspend fun getWxPayInfo(
        @FieldMap options: Map<String, String>
    ): WxPayInfoResponse


    /**
     * 还有这些接口没有调：
     * 申请会员/申请状态/
     */


    /**
     * 测试用
     */
    @GET("informationAct/list.html")
    fun getHomeData(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): HomeResponse

    companion object {
        const val BASE_URL = "https://java.xwfcx.com/newWfcx/"

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