package com.ck.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ck.data.*
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
class HomeViewModel @Inject internal constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {

    private var currentSearchResult: Flow<PagingData<HomeBean>>? = null
//    private var map: Map<String, String>? = null

//    fun setData(map: Map<String, String>) {
//        this.map = map
//    }

    fun getHomeData(queryString: String): Flow<PagingData<HomeBean>> {
        val newResult: Flow<PagingData<HomeBean>> =
            homeRepository.getHomeData(queryString).cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }

    suspend fun getHomeBanner(map: Map<String, String>): HomeResponse {
        return homeRepository.getHomeBanner(map)
    }

    suspend fun getCarList(map: Map<String, String>): CarResponse {
        return homeRepository.getCarList(map)
    }

    suspend fun getHomeNews(map: Map<String, String>): NewsResponse {
        return homeRepository.getHomeNews(map)
    }

//    val plantAndGardenPlantings: LiveData<List<CarBean>> =
//        homeRepository.getCarList2(map).asLiveData()


}