package com.ck.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ck.data.HomeBean
import com.ck.data.repository.HomeRepository
import kotlinx.coroutines.flow.Flow


/**
 *
 * @author ck
 * @date 2020/12/15
 */
class HomeViewModel @ViewModelInject constructor(
    private val repository: HomeRepository
) : ViewModel() {
    private var currentQueryValue: String? = null
    private var currentSearchResult: Flow<PagingData<HomeBean>>? = null

    fun getHomeData(queryString: String): Flow<PagingData<HomeBean>> {
        currentQueryValue = queryString
        val newResult: Flow<PagingData<HomeBean>> =
            repository.getHomeData(queryString).cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }

}