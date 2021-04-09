package com.ck.viewmodels

import android.util.Log
import android.widget.Toast
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ck.data.HomeBean
import com.ck.data.KeyResponse
import com.ck.data.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


/**
 *
 * @author ck
 * @date 2020/12/15
 */
class HomeViewModel @ViewModelInject constructor(
    private val repository: HomeRepository
) : ViewModel() {


//    fun getKey(): LiveData<KeyResponse> {
//        val newResult: LiveData<KeyResponse> = repository.getKey()
//        currentKeyResult = newResult
//        return newResult
//    }

//    fun getKey(): LiveData<KeyResponse> {
//        return repository.getKey();
//    }



    /**
     * 获取首页活动楼层数据
     */
    private var currentQueryValue: String? = null
    private var currentSearchResult: Flow<PagingData<HomeBean>>? = null

    fun getHomeData(queryString: String): Flow<PagingData<HomeBean>> {
        currentQueryValue = queryString
        val newResult: Flow<PagingData<HomeBean>> =
            repository.getHomeData(queryString).cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }

}