package com.ck.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ck.data.HomeBean
import com.ck.data.HomeResponse
import com.ck.data.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 *
 * @author ck
 * @date 2020/12/15
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {

    private var currentSearchResult: Flow<PagingData<HomeBean>>? = null

    fun getHomeData(queryString: String): Flow<PagingData<HomeBean>> {
        val newResult: Flow<PagingData<HomeBean>> =
            homeRepository.getHomeData(queryString).cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }

    suspend fun getHomeBanner(map: Map<String, String>): HomeResponse {
        return homeRepository.getHomeBanner(map)
    }


}