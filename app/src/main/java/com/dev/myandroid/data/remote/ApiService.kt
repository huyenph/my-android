package com.dev.myandroid.data.remote

import com.dev.myandroid.data.remote.adapter.NetworkResponse
import com.dev.myandroid.data.remote.response.ErrorResponse
import com.google.gson.JsonObject
import retrofit2.http.GET
import retrofit2.http.Query

typealias GenericResponse<S> = NetworkResponse<S, ErrorResponse>

interface ApiService {
    @GET("questions?key=Ll6gQIUA9QmrAmIknwxK3A((&pagesize=10")
    suspend fun getQuestions(
        @Query("site") site: String,
        @Query("page") page: Int
    ): GenericResponse<JsonObject>
}