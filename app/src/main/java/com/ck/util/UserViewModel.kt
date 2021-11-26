package com.ck.util

import android.app.Application
import androidx.lifecycle.*
import com.ck.data.LoginBean
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = UserRepository(application)

    val user = repository.user.asLiveData()

    fun updateHeadImg(headImg: String) = viewModelScope.launch {
        repository.updateHeadImg(headImg)
    }

    fun updateUser(loginBean: LoginBean) = viewModelScope.launch {
        repository.updateUser(loginBean)
    }

    fun logout() = viewModelScope.launch {
        repository.logout()
    }
}