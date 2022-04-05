package com.dev.myandroid.data.remote.adapter

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class NetworkResponseAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        // Suspend function wrap the response type in 'Call'
        if(Call::class.java != getRawType(returnType)) {
            return null
        }

        // Check first that the return type is 'ParameterizedType'
        check(returnType is ParameterizedType) {
            "return type must be parameterized as Call<NetworkResponse<<JsonObject>> or Call<NetworkResponse<out JsonObject>>"
        }

        // Get the response type inside the 'Call' type
        val responseType = getParameterUpperBound(0, returnType)

        // If the response type is not NetworkResponse then we can't handle this type, so return null
        if(getRawType(responseType) != NetworkResponse::class.java) {
            return null
        }

        // The response type is NetworkResponse and should be parameterized
        check(responseType is ParameterizedType) {
            "Response must be parameterized as NetworkResponse<JsonObject> or NetworkResponse<out JsonObject>"
        }

        val successBodyType = getParameterUpperBound(0, responseType)
        val errorBodyType = getParameterUpperBound(1, responseType)
        val errorBodyConverter = retrofit.nextResponseBodyConverter<Any>(null, errorBodyType, annotations)

        return NetworkResponseAdapter<Any, Any>(successBodyType, errorBodyConverter)
    }
}