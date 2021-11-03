package com.ck.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ck.api.ApiService
import com.ck.data.*
import com.ck.data.source.HomePagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 *
 * @author ck
 * @date 2020/12/16
 */
class HomeRepository @Inject constructor(private val service: ApiService) {

    companion object {
        private const val NETWORK_PAGE_SIZE = 20
    }

    fun getHomeData(query: String): Flow<PagingData<HomeBean>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = { HomePagingSource(service, query) }
        ).flow
    }

    suspend fun getHomeBanner(map: Map<String, String>): HomeResponse {
        return service.getNewsList(map)
    }

    suspend fun getCarList(map: Map<String, String>): CarResponse {
        return service.getCarList(map)
    }

    suspend fun getHomeNews(map: Map<String, String>): NewsResponse {
        return service.getHomeNews(map)
    }

    suspend fun submitCarHelp(map: Map<String, String>): BaseResponse {
        return service.submitCarHelp(map)
    }

//    suspend fun getCarList2(map: Map<String, String>?) = service.getCarList2(map)


}