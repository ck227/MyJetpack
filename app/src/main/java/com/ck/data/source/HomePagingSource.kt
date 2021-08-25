package com.ck.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ck.api.ApiService
import com.ck.data.HomeBean

private const val PAGE_INDEX = 1

/**
 *
 * @author ck
 * @date 2020/12/16
 */
class HomePagingSource(
    private val service: ApiService,
    private val query: String
) :
    PagingSource<Int, HomeBean>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HomeBean> {
        val page = params.key ?: PAGE_INDEX;
        return try {
            val response = service.getHomeData(page, params.loadSize)
            val cars = response.data
            LoadResult.Page(
                data = cars,
                prevKey = if (page == PAGE_INDEX) null else page - 1,
                nextKey = if (page == 2) null else page + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, HomeBean>): Int? {
        TODO("Not yet implemented")
    }

}