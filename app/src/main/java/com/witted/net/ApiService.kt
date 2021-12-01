package com.witted.net

import com.witted.bean.HospitalBean
import com.witted.net.ApiResponse
import retrofit2.http.*

interface ApiService {

//    @GET("wxarticle/chapters/json")
//    suspend fun getWxArticle(): ApiResponse<List<WxArticleBean>>
//
//    @GET("abc/chapters/json")
//    suspend fun getWxArticleError(): ApiResponse<List<WxArticleBean>>
//
//    @FormUrlEncoded
//    @POST("user/login")
//    suspend fun login(@Field("username") userName: String, @Field("password") passWord: String): ApiResponse<User?>
//
//    companion object {
//         var BASE_URL = "https://wanandroid.com/"
//    }


    //获取医院数据
    @GET("/extra/room/hospital")
    suspend fun getHospitalData(@Query("dev_id") devId: String): ApiResponse<HospitalBean>


}