package com.ck.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ck.api.ApiService
import com.ck.data.CarBean
import java.util.HashMap

private const val PAGE_INDEX = 1
private const val PAGE_SIZE = 10

class CarPagingSource(private val service: ApiService, private val query: String) :
    PagingSource<Int, CarBean>() {

    override fun getRefreshKey(state: PagingState<Int, CarBean>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CarBean> {
        val page = params.key ?: PAGE_INDEX
        return try {
            val map: MutableMap<String, String> = HashMap()
            map["carName"] = query
            map["page"] = page.toString()
            map["limit"] = PAGE_SIZE.toString()
            val response = service.getCarList(map)
            val nextKey = if (response.data.isEmpty()) {
                null
            } else {
                page + 1
            }
            LoadResult.Page(
                data = response.data,
                prevKey = if (page == PAGE_INDEX) null else page - 1,
                nextKey = nextKey
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}