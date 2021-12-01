package com.ck.util

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ck.data.LoginBean
import kotlinx.coroutines.launch

/**
 * UserViewModel只更新本地的dataStore数据
 */
class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = UserRepository(application)

    val user = repository.user.asLiveData()

    fun updateHeadImg(headImg: String) = viewModelScope.launch {
        repository.updateHeadImg(headImg)
    }

    fun updateNickName(nickName: String) = viewModelScope.launch {
        repository.updateNickName(nickName)
    }

    fun updateGender(isMale: Boolean) = viewModelScope.launch {
        repository.updateGender(isMale)
    }

    fun updateSignature(signature: String) = viewModelScope.launch {
        repository.updateSignature(signature)
    }

    fun updateUser(loginBean: LoginBean) = viewModelScope.launch {
        repository.updateUser(loginBean)
    }

    fun logout() = viewModelScope.launch {
        repository.logout()
    }

}