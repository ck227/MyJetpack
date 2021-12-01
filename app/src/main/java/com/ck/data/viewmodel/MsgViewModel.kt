package com.ck.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ck.data.CarBean
import com.ck.data.MsgBean
import com.ck.data.repository.MsgRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MsgViewModel @Inject internal constructor(
    private val msgRepository: MsgRepository
) : ViewModel() {
    private var msgListResult: Flow<PagingData<MsgBean>>? = null

    init {
        //消息
        getMsgList()
    }

    /**
     * 消息中心
     */
    fun getMsgList(): Flow<PagingData<MsgBean>> {
        val lastResult = msgListResult
        if (lastResult != null) {
            return lastResult
        }
        val newResult: Flow<PagingData<MsgBean>> = msgRepository.getMsgList()
            .cachedIn(viewModelScope)
        msgListResult = newResult
        return newResult
    }
}