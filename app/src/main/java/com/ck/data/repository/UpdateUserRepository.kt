package com.ck.data.repository

import com.ck.api.ApiService
import com.ck.data.BaseResponse
import com.ck.data.UploadPicResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class UpdateUserRepository @Inject constructor(private val service: ApiService) {

    suspend fun uploadPic(body: RequestBody, file: MultipartBody.Part): UploadPicResponse {
        return service.uploadPic(body, file)
    }

    suspend fun updateUserInfo(map: Map<String, String>): BaseResponse {
        return service.updateUserInfo(map)
    }
    
}