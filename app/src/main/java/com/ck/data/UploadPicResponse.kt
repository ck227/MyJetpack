package com.ck.data

import com.google.gson.annotations.SerializedName

data class UploadPicResponse(
    @field:SerializedName("status") val status: String,
    @field:SerializedName("message") val message: String,
    @field:SerializedName("msg") val msg: String,
)


