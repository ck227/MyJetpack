package com.ck.data.repository

import com.ck.api.ApiService
import com.ck.data.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class UpdateUserRepository @Inject constructor(private val service: ApiService) {

    suspend fun login(map: Map<String, String>): LoginResponse {
        return service.login(map)
    }

    suspend fun getCode(map: Map<String, String>): BaseResponse {
        return service.getCode(map)
    }

    suspend fun findPwd(map: Map<String, String>): BaseResponse {
        return service.findPwd(map)
    }

    suspend fun register(map: Map<String, String>): BaseResponse {
        return service.register(map)
    }

    suspend fun uploadPic(body: RequestBody, file: MultipartBody.Part): UploadPicResponse {
        return service.uploadPic(body, file)
    }

    suspend fun updateUserInfo(map: Map<String, String>): BaseResponse {
        return service.updateUserInfo(map)
    }

    suspend fun updatePwd(map: Map<String, String>): BaseResponse {
        return service.findPwd(map)
    }

    suspend fun getChargeHistory(map: Map<String, String>): ChargeHistoryResponse {
        return service.getChargeHistory(map)
    }

    suspend fun getWxPayInfo(map: Map<String, String>): WxPayInfoResponse {
        return service.getWxPayInfo(map)
    }



}