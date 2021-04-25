package com.ck.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ck.api.ApiService
import com.ck.data.HomeBean
import com.ck.data.source.HomePagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 *
 * @author ck
 * @date 2020/12/16
 */
class HomeRepository @Inject constructor(private val service: ApiService) {

    fun getHomeData(query: String): Flow<PagingData<HomeBean>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = { HomePagingSource(service, query) }
        ).flow
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 20
    }
}