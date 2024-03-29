package com.ck.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ck.data.*
import com.ck.data.repository.CarRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarViewModel @Inject internal constructor(
    private val carRepository: CarRepository,
) : ViewModel() {

    private var carDetailResult: CarDetailResponse? = null
    private var currentSearchResult: Flow<PagingData<CarBean>>? = null

    /**
     * 分页获取所有车辆
     */
    fun getCars(map: MutableMap<String, String>): Flow<PagingData<CarBean>> {
        val lastResult = currentSearchResult
//        if (lastResult != null) {
//            return lastResult
//        }
//        currentQueryValue = queryString
        val newResult: Flow<PagingData<CarBean>> = carRepository.getCarList(map)
            .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }

    private val _carDetail: MutableLiveData<CarDetailResponse> = MutableLiveData()
    val carDetail: LiveData<CarDetailResponse> get() = _carDetail

    /**
     * 不建议用map，需要用carID判断一下是否一样再从里面取值，否者可能取得是上一次请求接口的值
     */
    fun getCarDetail(map: Map<String, String>) {
        viewModelScope.launch {
            val result = carRepository.getCarDetail(map)
            _carDetail.value = result
        }
    }

    private val _updateCollectResponse: MutableLiveData<BaseResponse> = MutableLiveData()
    val updateCollectResponse: LiveData<BaseResponse> get() = _updateCollectResponse
    fun updateCollect(map: Map<String, String>) {
        viewModelScope.launch {
            val result = carRepository.updateCollect(map)
            _updateCollectResponse.value = result
        }
    }

}