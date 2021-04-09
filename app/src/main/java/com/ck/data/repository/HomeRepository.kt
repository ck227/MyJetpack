package com.ck.data.repository

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ck.api.ApiService
import com.ck.data.HomeBean
import com.ck.data.KeyResponse
import com.ck.data.source.HomePagingSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


/**
 *
 * @author ck
 * @date 2020/12/16
 */
class HomeRepository @Inject constructor(private val service: ApiService) {

    companion object {
        private const val NETWORK_PAGE_SIZE = 10
    }


    /*suspend fun getKey(): LiveData<KeyResponse> {
        val data = MutableLiveData<KeyResponse>()
        withContext(Dispatchers.Main) {
            service.getKey().enqueue(object : Callback<KeyResponse> {
                override fun onResponse(call: Call<KeyResponse>, response: Response<KeyResponse>) {
                    data.value = response.body()
                }

                // Error case is left out for brevity.
                override fun onFailure(call: Call<KeyResponse>, t: Throwable) {
//                TODO()
                }
            })
        }
        return data
    }*/

    fun getKey(): LiveData<KeyResponse> {
        val data = MutableLiveData<KeyResponse>()
        service.getKey().enqueue(object : Callback<KeyResponse> {
            override fun onResponse(call: Call<KeyResponse>, response: Response<KeyResponse>) {
                data.value = response.body()
            }
            // Error case is left out for brevity.
            override fun onFailure(call: Call<KeyResponse>, t: Throwable) {
                TODO()
                print("hello")
//                Toast.makeText(,"hello",Toast.LENGTH_LONG)
            }
        })
        return data
    }


    fun getHomeData(query: String): Flow<PagingData<HomeBean>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = { HomePagingSource(service, query) }
        ).flow
    }


}