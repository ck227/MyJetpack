package com.ck.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ck.api.ApiService
import com.ck.data.MsgBean
import com.ck.data.source.MsgPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MsgRepository @Inject constructor(private val service: ApiService) {

    companion object {
        private const val NETWORK_PAGE_SIZE = 10
    }

    fun getMsgList(): Flow<PagingData<MsgBean>> {

        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MsgPagingSource(service) }
        ).flow
    }
}