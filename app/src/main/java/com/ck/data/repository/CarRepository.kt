package com.ck.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ck.api.ApiService
import com.ck.data.*
import com.ck.data.source.CarPagingSource
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class CarRepository @Inject constructor(private val service: ApiService) {

    companion object {
        private const val NETWORK_PAGE_SIZE = 10
    }

    fun getCarList(map: MutableMap<String, String>): Flow<PagingData<CarBean>> {

        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { CarPagingSource(service, map) }
        ).flow
    }

    suspend fun getCarDetail(map: Map<String, String>): CarDetailResponse {
        return service.getCarDetail(map)
    }

    suspend fun login(map: Map<String, String>): LoginResponse {
        return service.login(map)
    }

    suspend fun getCode(map: Map<String, String>): BaseResponse {
        return service.getCode(map)
    }

    suspend fun register(map: Map<String, String>): BaseResponse {
        return service.register(map)
    }

    suspend fun findPwd(map: Map<String, String>): BaseResponse {
        return service.findPwd(map)
    }

    suspend fun uploadPic(body: RequestBody, file: MultipartBody.Part): UploadPicResponse {
        return service.uploadPic(body, file)
    }

    suspend fun updateUserInfo(map: Map<String, String>): BaseResponse {
        return service.updateUserInfo(map)
    }


}