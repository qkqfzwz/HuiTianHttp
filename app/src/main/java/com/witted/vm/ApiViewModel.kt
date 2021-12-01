package com.witted.vm

import android.util.Log
import androidx.lifecycle.*
import com.aisier.network.observer.StateLiveData
import com.witted.bean.HospitalBean
import com.witted.net.ApiResponse
import com.witted.test.WxArticleBean
import com.witted.test.WxArticleRepository

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

import kotlinx.coroutines.launch


class ApiViewModel : ViewModel() {

    private val TAG: String = "ApiViewModel"
    private val repository by lazy { WxArticleRepository() }

    private val dbLiveData = StateLiveData<List<WxArticleBean>>()
    private val apiLiveData = StateLiveData<List<WxArticleBean>>()
    val mediatorLiveDataLiveData = MediatorLiveData<ApiResponse<List<WxArticleBean>>>().apply {
        this.addSource(apiLiveData) {
            this.value = it
        }
        this.addSource(dbLiveData) {
            this.value = it
        }
    }

    val wxArticleLiveData = StateLiveData<List<WxArticleBean>>()


    fun requestNet() {
//        viewModelScope.launch {
//            flow<ApiResponse<List<WxArticleBean>>>{
//                repository.fetchWxArticleFromNet().let { emit(it) }
//            }.onStart {
//                Log.i(TAG, "requestNet:_onStart "+"onStart")
//            }.onCompletion {
//                Log.i(TAG, "requestNet:_onCompletion "+"onCompletion")
//            }.collect{
//                Log.i(TAG, "requestNet_collect: "+it?.toString())
//                it.let {
//                    wxArticleLiveData.value=it
//                }
//            }
//        }
    }


    val hospitalLiveData = StateLiveData<HospitalBean>()

    fun getHospital(devId: String) {
        viewModelScope.launch {
            Log.i(TAG, "getHospital: _launch "+Thread.currentThread())
            flow<ApiResponse<HospitalBean>> {
                emit(repository.getHospitalData(devId))
                Log.i(TAG, "getHospital: _emit "+Thread.currentThread())
            }.onStart {
                Log.i(TAG, "getHospital: _onStart"+Thread.currentThread())
            }.onCompletion {
                Log.i(TAG, "getHospital: _onCompletion"+Thread.currentThread())
            }.collect {
                Log.i(TAG, "getHospital: _collect"+Thread.currentThread())
                hospitalLiveData.value=it
            }

        }

//        hospitalLiveData.value = repository.getHospitaiData(devId)

    }


//
//    fun requestNetError() {
//        viewModelScope.launch {
//            wxArticleLiveData.value = repository.fetchWxArticleError()
//        }
//    }
//
//    fun requestFromNet() {
//        viewModelScope.launch {
//            apiLiveData.value = repository.fetchWxArticleFromNet()
//        }
//    }
//
//    fun requestFromDb() {
//        viewModelScope.launch {
//            dbLiveData.value = repository.fetchWxArticleFromDb()
//        }
//    }
//
//    /**
//     * 该请求使用Flow优化，自带loading。
//     */
//    fun login(username: String, password: String) {
//        launchWithLoading(requestBlock = {
//            repository.login(username, password)
//        }, resultCallback = {
//            userLiveData.value = it
//        })
//    }
}