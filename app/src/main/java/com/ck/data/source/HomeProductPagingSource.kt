package com.ck.data.source

import androidx.paging.PagingSource
import com.ck.api.ApiService
import com.ck.data.HomeBean

private const val PAGE_INDEX = 1

/**
 *
 * @author ck
 * @date 2021/4/20
 */
class HomeProductPagingSource(
    private val service: ApiService
) :
    PagingSource<Int, HomeBean>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HomeBean> {
        val page = params.key ?: PAGE_INDEX;
        return try {
            val response = service.getHomeProducts("000000")
            val cars = response.data
            LoadResult.Page(
                data = cars,
                prevKey = if (page == PAGE_INDEX) null else page - 1,
                //首页只有一页数据
                nextKey = if (page == 1) null else page + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

}