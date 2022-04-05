package com.dev.myandroid.data.remote.response

import com.dev.myandroid.domain.model.BaseModel
import com.google.gson.annotations.SerializedName

class ErrorResponse(
    @SerializedName("error_id") val errorId: Int = 0,
    @SerializedName("error_message") val errorMessage: String? = null,
    @SerializedName("error_name") val errorName: String? = null,
): BaseModel()