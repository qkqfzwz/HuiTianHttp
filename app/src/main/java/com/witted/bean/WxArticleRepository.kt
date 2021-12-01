package com.witted.test


import com.witted.bean.HospitalBean
import com.witted.net.ApiResponse
import com.witted.net.BaseRepository
import com.witted.net.RetrofitClient


class WxArticleRepository : BaseRepository() {

    private val mService by lazy {
        RetrofitClient.service
    }

    suspend fun getHospitalData(devId: String): ApiResponse<HospitalBean> {
        return executeHttp {
            mService.getHospitalData(devId)
        }
    }

//    suspend fun fetchWxArticleFromNet(): ApiResponse<List<WxArticleBean>> {
//        return executeHttp {
//            mService.getWxArticle()
//        }
//    }

//    suspend fun fetchWxArticleFromDb(): ApiResponse<List<WxArticleBean>> {
//        return getWxArticleFromDatabase()
//    }

//    suspend fun fetchWxArticleError(): ApiResponse<List<WxArticleBean>> {
//        return executeHttp {
//            mService.getWxArticleError()
//        }
//    }
//
//    suspend fun login(username: String, password: String): ApiResponse<User?> {
//        return executeHttp {
//            mService.login(username, password)
//        }
//    }

//    private suspend fun getWxArticleFromDatabase(): ApiResponse<List<WxArticleBean>> = withContext(Dispatchers.IO) {
//        val bean = WxArticleBean()
//        bean.id = 999
//        bean.name = "零先生"
//        bean.visible = 1
//        ApiSuccessResponse(arrayListOf(bean))
//    }


}