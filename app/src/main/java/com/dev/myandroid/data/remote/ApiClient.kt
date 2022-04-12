package com.dev.myandroid.data.remote

import com.dev.myandroid.data.remote.adapter.NetworkResponse
import com.dev.myandroid.data.remote.response.ErrorResponse
import com.google.gson.JsonObject
import java.lang.reflect.Type

class ApiClient(private val responseListener: ApiResponseListener) {
    fun request(code: Int, type: Type?, response: NetworkResponse<JsonObject, ErrorResponse>) {
        when (response) {
            is NetworkResponse.Success -> responseListener.onSuccess(code, type, response.body)
            is NetworkResponse.Failure -> responseListener.onFailure(code, response.body)
            is NetworkResponse.NetworkError -> responseListener.onNetworkError(response.code)
            is NetworkResponse.UnknownError -> responseListener.onUnknownError(response.code)
        }
    }

    interface ApiResponseListener {
        fun onSuccess(code: Int, type: Type?, response: JsonObject)
        fun onFailure(code: Int, errorResponse: ErrorResponse)
        fun onNetworkError(code: Int)
        fun onUnknownError(code: Int)
    }
}