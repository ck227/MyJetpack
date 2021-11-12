package com.ck.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ck.data.CarBean
import com.ck.data.CarDetailResponse
import com.ck.data.CarResponse
import com.ck.data.repository.CarRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarViewModel @Inject internal constructor(
    private val carRepository: CarRepository
) : ViewModel() {

    private var currentQueryValue: String? = null
    private var currentSearchResult: Flow<PagingData<CarBean>>? = null

    init {
        //车库
//        getCars("")
    }

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

    fun getCarDetail(map: Map<String, String>) {
        viewModelScope.launch {
            val result = carRepository.getCarDetail(map)
            _carDetail.value = result
        }
    }




}