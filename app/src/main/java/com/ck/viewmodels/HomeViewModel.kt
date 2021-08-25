package com.ck.viewmodels

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ck.data.*
import com.ck.data.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.HashMap
import javax.inject.Inject


/**
 *
 * @author ck
 * @date 2020/12/15
 */
@HiltViewModel
class HomeViewModel @Inject internal constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {

    init {
        //获取banner
        val map: MutableMap<String, String> = HashMap()
        map["limit"] = "2"
        map["page"] = "1"
        map["informationType"] = "1"
        getHomeBanner(map)

        //限时折扣
        val map2: MutableMap<String, String> = HashMap()
        map2["limit"] = "2"
        map2["page"] = "1"
        map2["carType"] = "2"
        getDiscountCars(map2)

        //热门推荐
        val map3: MutableMap<String, String> = HashMap()
        map3["limit"] = "4"
        map3["page"] = "1"
        map3["carType"] = "1"
        getSuggestCars(map3)

        //资讯中心
        val map4: MutableMap<String, String> = HashMap()
        map4["limit"] = "2"
        map4["page"] = "1"
        map4["informationType"] = "2"
        getHomeNews(map4)


    }

    ///banner开始
    private val _banners: MutableLiveData<HomeResponse> = MutableLiveData()
    val banners: LiveData<HomeResponse> get() = _banners
    private fun getHomeBanner(map: Map<String, String>) {
        viewModelScope.launch {
            val result = homeRepository.getHomeBanner(map)
            _banners.value = result
        }
    }
    ///banner结束

    ///限时折扣开始
    private val _discountCars: MutableLiveData<CarResponse> = MutableLiveData()
    val discountCars: LiveData<CarResponse> get() = _discountCars
    private fun getDiscountCars(map: Map<String, String>) {
        viewModelScope.launch {
            val result = homeRepository.getCarList(map)
            _discountCars.value = result
        }
    }
    ///限时折扣结束

    ///热门推荐开始
    private val _suggestCars: MutableLiveData<CarResponse> = MutableLiveData()
    val suggestCars: LiveData<CarResponse> get() = _suggestCars
    private fun getSuggestCars(map: Map<String, String>) {
        viewModelScope.launch {
            val result = homeRepository.getCarList(map)
            _suggestCars.value = result
        }
    }
    ///热门推荐结束

    ///资讯中心开始
    private val _homeNews: MutableLiveData<NewsResponse> = MutableLiveData()
    val homeNews: LiveData<NewsResponse> get() = _homeNews
    private fun getHomeNews(map: Map<String, String>) {
        viewModelScope.launch {
            val result = homeRepository.getHomeNews(map)
            _homeNews.value = result
        }
    }
    //资讯中心结束




    suspend fun getHomeNews2(map: Map<String, String>): NewsResponse {
        return homeRepository.getHomeNews(map)
    }


    fun getHomeData(queryString: String): Flow<PagingData<HomeBean>> {
        return homeRepository.getHomeData(queryString).cachedIn(viewModelScope)
    }

}