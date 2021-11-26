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
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

@HiltViewModel
class CarViewModel @Inject internal constructor(
    private val carRepository: CarRepository,
) : ViewModel() {

    private var carDetailResult: CarDetailResponse? = null
    private var currentQueryValue: String? = null
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

    //获取登录用户信息
    /*fun getLoginData() {
        viewModelScope.launch {

            val userKeyFlow: Flow<String> = dataStore.data
                .map { preferences ->
                    preferences[userKey] ?: ""
                }
            userKeyFlow.collect { json ->
                val gson = Gson()
                val loginBean = gson.fromJson(json, LoginBean::class.java)
                _loginBean.value = loginBean
//                if (loginBean == null) {
//                    Toast.makeText(context, "空", Toast.LENGTH_SHORT).show()
//                }
            }
        }
    }*/

    //登录
    private val _loginResponse: MutableLiveData<LoginResponse> = MutableLiveData()
    val loginResponse: LiveData<LoginResponse> get() = _loginResponse

    private val _loginBean: MutableLiveData<LoginBean> = MutableLiveData()
    val loginBean: LiveData<LoginBean> get() = _loginBean

    fun login(map: Map<String, String>) {
        viewModelScope.launch {
            val result = carRepository.login(map)
            _loginResponse.value = result
            _loginBean.value = result.data

        }
    }

    //获取验证码
    private val _getCodeResponse: MutableLiveData<BaseResponse> = MutableLiveData()
    val getCodeResponse: LiveData<BaseResponse> get() = _getCodeResponse

    fun getCode(map: Map<String, String>) {
        viewModelScope.launch {
            val result = carRepository.getCode(map)
            _getCodeResponse.value = result
        }
    }


    //注册
    private val _registerResponse: MutableLiveData<BaseResponse> = MutableLiveData()
    val registerResponse: LiveData<BaseResponse> get() = _registerResponse

    fun register(map: Map<String, String>) {
        viewModelScope.launch {
            val result = carRepository.register(map)
            _registerResponse.value = result
        }
    }

    //找回密码
    private val _findPwdResponse: MutableLiveData<BaseResponse> = MutableLiveData()
    val findPwdResponse: LiveData<BaseResponse> get() = _findPwdResponse

    fun findPwd(map: Map<String, String>) {
        viewModelScope.launch {
            val result = carRepository.findPwd(map)
            _findPwdResponse.value = result
        }
    }

    //上传头像
    private val _uploadPicResponse: MutableLiveData<UploadPicResponse> = MutableLiveData()
    val uploadPicResponse: LiveData<UploadPicResponse> get() = _uploadPicResponse

    fun uploadPic(body: RequestBody, file: MultipartBody.Part) {
        viewModelScope.launch {
            val result = carRepository.uploadPic(body, file)
            _uploadPicResponse.value = result
        }
    }

    //更新用户信息
    private val _updateUserInfoResponse :MutableLiveData<BaseResponse> = MutableLiveData()
    val updateUserInfoResponse:LiveData<BaseResponse> get() = _updateUserInfoResponse

    fun updateUserInfo(map: Map<String, String>){
        viewModelScope.launch {
            val result = carRepository.updateUserInfo(map)
            _updateUserInfoResponse.value = result
        }
    }


}