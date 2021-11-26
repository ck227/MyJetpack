package com.ck.util

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ck.data.LoginBean
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = UserRepository(application)

    val user = repository.user.asLiveData()

    fun updateUser(loginBean: LoginBean) = viewModelScope.launch {
        repository.saveUserBean2(loginBean)
    }
}