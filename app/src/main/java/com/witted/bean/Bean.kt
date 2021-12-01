package com.witted.bean

import java.io.Serializable


data class ErrorBean(
        var status: Int,
        var  msg:String,

)



//医院信息
data class HospitalBean(
        val hospital_name: String? = null,
        val hospital_cn: String? = null,
        val hospital_en: String? = null,
        val hospital_icon: String? = null,
        val area_info: AreaInfo? = null
) : Serializable


//地区信息
data class AreaInfo(
        val area_code: String? = null,
        val area_name: String? = null
) : Serializable