package com.ck.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ck.data.BaseResponse
import com.ck.data.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarHelpViewModel @Inject internal constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {
    init {

    }

    private val _response: MutableLiveData<BaseResponse> = MutableLiveData()
    val response: LiveData<BaseResponse> get() = _response

    fun submitCarHelp(map: Map<String, String>) {
        viewModelScope.launch {
            val result = homeRepository.submitCarHelp(map)
            _response.value = result
        }
    }
}