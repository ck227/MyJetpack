package com.ck.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ck.data.CarBean
import com.ck.data.repository.CarRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CarViewModel @Inject internal constructor(
    private val carRepository: CarRepository
) : ViewModel() {

    private var currentQueryValue: String? = null
    private var currentSearchResult: Flow<PagingData<CarBean>>? = null

    init {
        //车库
//        val map: MutableMap<String, String> = HashMap()
//        map["brandId"] = ""
//        getAllCars(map)
//        getCars()
        getCars("")
    }

    /**
     * 分页获取所有车辆
     */
     fun getCars(queryString: String): Flow<PagingData<CarBean>> {
        val lastResult = currentSearchResult
        if (queryString == currentQueryValue && lastResult != null) {
            return lastResult
        }
        currentQueryValue = queryString
        val newResult: Flow<PagingData<CarBean>> = carRepository.getCarList(queryString)
            .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }

    ///所有车辆
//    private val _allCars: MutableLiveData<CarResponse> = MutableLiveData()
//    val allCars: LiveData<CarResponse> get() = _allCars
//    private fun getAllCars(map: Map<String, String>) {
//        viewModelScope.launch {
//            val result = homeRepository.getCarList(map)
//            _allCars.value = result
//        }
//    }
    ///所有车辆


}