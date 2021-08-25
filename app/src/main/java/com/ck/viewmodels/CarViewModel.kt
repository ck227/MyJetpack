package com.ck.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ck.data.CarResponse
import com.ck.data.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.HashMap
import javax.inject.Inject

@HiltViewModel
class CarViewModel @Inject internal constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {
    init {
        //车库
        val map: MutableMap<String, String> = HashMap()
        map["brandId"] = ""
        getAllCars(map)
    }

    ///所有车辆
    private val _allCars: MutableLiveData<CarResponse> = MutableLiveData()
    val allCars: LiveData<CarResponse> get() = _allCars
    private fun getAllCars(map: Map<String, String>) {
        viewModelScope.launch {
            val result = homeRepository.getCarList(map)
            _allCars.value = result
        }
    }
    ///所有车辆
}