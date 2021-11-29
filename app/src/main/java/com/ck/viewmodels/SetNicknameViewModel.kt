package com.ck.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.ck.data.BaseResponse
import com.ck.data.repository.CarRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
@HiltViewModel
class SetNicknameViewModel @Inject internal constructor(
    private val carRepository: CarRepository,
) : ViewModel() {

    //更新用户信息
    private val _updateUserInfoResponse: MutableLiveData<BaseResponse> = MutableLiveData()
    val updateUserInfoResponse: LiveData<BaseResponse> get() = _updateUserInfoResponse

    fun updateUserInfo(map: Map<String, String>) {
        viewModelScope.launch {
            val result = carRepository.updateUserInfo(map)
            _updateUserInfoResponse.value = result
        }
    }
}*/

//class SetNicknameViewModel(application: Application) : AndroidViewModel(application) {
//    private val carRepository = CarRepository
//
//    fun updateNickName(nickName: String) = viewModelScope.launch {
//        carRepository.updateNickName(nickName)
//    }
//}
