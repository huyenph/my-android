package com.dev.myandroid.data.remote.adapter

import java.io.IOException

sealed class NetworkResponse<out S : Any, out E : Any> {
    /**
     * Success response with body
     */
    data class Success<S : Any>(val body: S, val code: Int) : NetworkResponse<S, Nothing>()

    /**
     * Failure response with body
     */
    data class Failure<E : Any>(val body: E, val code: Int) : NetworkResponse<Nothing, E>()

    /**
     * Network error
     */
    data class NetworkError(val error: IOException, val code: Int) :
        NetworkResponse<Nothing, Nothing>()

    /**
     * Ex: JSON parse error
     */
    data class UnknownError(val error: Throwable?, val code: Int) :
        NetworkResponse<Nothing, Nothing>()
}