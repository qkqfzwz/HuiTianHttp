package com.aisier.network.observer

import androidx.lifecycle.Observer
import com.witted.net.*


abstract class IStateObserver<T> : Observer<ApiResponse<T>> {

    override fun onChanged(apiResponse: ApiResponse<T>) {
        when (apiResponse) {
            is ApiSuccessResponse -> onSuccess(apiResponse.response)
            is ApiEmptyResponse -> onDataEmpty()
            is ApiFailedResponse -> onFailed(apiResponse.status, apiResponse.text)
            is ApiErrorResponse -> onError(apiResponse.throwable)
        }
        onComplete()
    }

    abstract fun onSuccess(data: T)

    abstract fun onDataEmpty()

    abstract fun onError(e: Throwable)

    abstract fun onComplete()

    abstract fun onFailed(errorCode: Int?, errorMsg: String?)

}