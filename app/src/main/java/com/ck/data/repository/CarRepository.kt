package com.ck.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ck.api.ApiService
import com.ck.data.CarBean
import com.ck.data.source.CarPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CarRepository @Inject constructor(private val service: ApiService) {

    companion object {
        private const val NETWORK_PAGE_SIZE = 10
    }

    fun getCarList(query: String): Flow<PagingData<CarBean>> {

        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { CarPagingSource(service, query) }
        ).flow
    }
}