package com.dev.myandroid.data.remote

import com.dev.myandroid.data.remote.response.ErrorResponse
import com.google.gson.JsonObject
import java.lang.reflect.Type

class ApiClient(private val responseListener: ApiResponseListener) {

    interface ApiResponseListener {
        fun onSuccess(code: Int, type: Type?, response: JsonObject)
        fun onFailure(code: Int, errorResponse: ErrorResponse)
        fun onNetworkError(code: Int)
        fun onUnknownError(code: Int)
    }
}