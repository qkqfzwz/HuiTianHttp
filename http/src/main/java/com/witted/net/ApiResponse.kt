package com.witted.net

import java.io.Serializable

open class ApiResponse<T>(
        open val result: T? = null,
        open val status: Int? = null,
        open val text: String? = null,
        open val error: String? = null,
        open val errorT: Throwable? = null,
        ) : Serializable {
    val isSuccess: Boolean
        get() = status == 200
}

data class ApiSuccessResponse<T>(val response: T) : ApiResponse<T>(result = response)

class ApiEmptyResponse<T> : ApiResponse<T>()

data class ApiFailedResponse<T>(override val status: Int?, override val text: String?) : ApiResponse<T>(status = status, text = text)

data class ApiErrorResponse<T>(val throwable: Throwable) : ApiResponse<T>( errorT= throwable)
