package com.ck.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ck.data.BaseResponse
import com.ck.data.ChargeHistoryResponse
import com.ck.data.LoginResponse
import com.ck.data.UploadPicResponse
import com.ck.data.repository.UpdateUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject


@HiltViewModel
class UpdateUserViewModel @Inject internal constructor(
    private val updateUserRepository: UpdateUserRepository
) : ViewModel() {

    /**
     * 登录
     */
    private val _loginResponse: MutableLiveData<LoginResponse> = MutableLiveData()
    val loginResponse: LiveData<LoginResponse> get() = _loginResponse

    fun login(map: Map<String, String>) {
        viewModelScope.launch {
            val result = updateUserRepository.login(map)
            _loginResponse.value = result
        }
    }

    /**
     * 获取验证码
     */
    private val _getCodeResponse: MutableLiveData<BaseResponse> = MutableLiveData()
    val getCodeResponse: LiveData<BaseResponse> get() = _getCodeResponse

    fun getCode(map: Map<String, String>) {
        viewModelScope.launch {
            val result = updateUserRepository.getCode(map)
            _getCodeResponse.value = result
        }
    }

    /**
     * 找回密码
     */
    private val _findPwdResponse: MutableLiveData<BaseResponse> = MutableLiveData()
    val findPwdResponse: LiveData<BaseResponse> get() = _findPwdResponse

    fun findPwd(map: Map<String, String>) {
        viewModelScope.launch {
            val result = updateUserRepository.findPwd(map)
            _findPwdResponse.value = result
        }
    }

    /**
     * 注册
     */
    private val _registerResponse: MutableLiveData<BaseResponse> = MutableLiveData()
    val registerResponse: LiveData<BaseResponse> get() = _registerResponse

    fun register(map: Map<String, String>) {
        viewModelScope.launch {
            val result = updateUserRepository.register(map)
            _registerResponse.value = result
        }
    }

    /**
     * 上传头像
     */
    private val _uploadPicResponse: MutableLiveData<UploadPicResponse> = MutableLiveData()
    val uploadPicResponse: LiveData<UploadPicResponse> get() = _uploadPicResponse

    fun uploadPic(body: RequestBody, file: MultipartBody.Part) {
        viewModelScope.launch {
            val result = updateUserRepository.uploadPic(body, file)
            _uploadPicResponse.value = result
        }
    }

    /**
     * 更新昵称
     */
    private val _updateNickNameResponse: MutableLiveData<BaseResponse> = MutableLiveData()
    val updateNickNameResponse: LiveData<BaseResponse> get() = _updateNickNameResponse
    fun updateNickName(map: Map<String, String>) {
        viewModelScope.launch {
            val result = updateUserRepository.updateUserInfo(map)
            _updateNickNameResponse.value = result
        }
    }

    /**
     * 更新头像
     */
    private val _updateHeadImgResponse: MutableLiveData<BaseResponse> = MutableLiveData()
    val updateHeadImgResponse: LiveData<BaseResponse> get() = _updateHeadImgResponse
    fun updateHeadImg(map: Map<String, String>) {
        viewModelScope.launch {
            val result = updateUserRepository.updateUserInfo(map)
            _updateHeadImgResponse.value = result
        }
    }

    /**
     * 更新性别
     */
    private val _updateGenderResponse: MutableLiveData<BaseResponse> = MutableLiveData()
    val updateGenderResponse: LiveData<BaseResponse> get() = _updateGenderResponse
    fun updateGender(map: Map<String, String>) {
        viewModelScope.launch {
            val result = updateUserRepository.updateUserInfo(map)
            _updateGenderResponse.value = result
        }
    }

    /**
     * 更新签名
     */
    private val _updateSignatureResponse: MutableLiveData<BaseResponse> = MutableLiveData()
    val updateSignatureResponse: LiveData<BaseResponse> get() = _updateSignatureResponse
    fun updateSignature(map: Map<String, String>) {
        viewModelScope.launch {
            val result = updateUserRepository.updateUserInfo(map)
            _updateSignatureResponse.value = result
        }
    }

    /**
     * 更新密码
     */
    private val _updatePwdResponse: MutableLiveData<BaseResponse> = MutableLiveData()
    val updatePwdResponse: LiveData<BaseResponse> get() = _updatePwdResponse
    fun updatePwd(map: Map<String, String>) {
        viewModelScope.launch {
            val result = updateUserRepository.updatePwd(map)
            _updatePwdResponse.value = result
        }
    }

    /**
     * 获取充值记录
     */
    private val _chargeHistoryResponse: MutableLiveData<ChargeHistoryResponse> = MutableLiveData()
    val chargeHistoryResponse: LiveData<ChargeHistoryResponse> get() = _chargeHistoryResponse
    fun getChargeHistory(map: Map<String, String>) {
        viewModelScope.launch {
            val result = updateUserRepository.getChargeHistory(map)
            _chargeHistoryResponse.value = result
        }
    }


}
