package com.ck.viewmodels

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ck.data.*
import com.ck.data.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
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

    private val _banners: MutableLiveData<HomeResponse> = MutableLiveData()
    val banners: LiveData<HomeResponse> get() = _banners

    fun getHomeData(queryString: String): Flow<PagingData<HomeBean>> {
        return homeRepository.getHomeData(queryString).cachedIn(viewModelScope)
    }


    fun getHomeBanner(map: Map<String, String>) {
        viewModelScope.launch {
            val result = homeRepository.getHomeBanner(map)
            _banners.value = result
        }
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