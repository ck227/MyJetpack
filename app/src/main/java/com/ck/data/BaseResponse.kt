package com.ck.data

import com.google.gson.annotations.SerializedName


/**
 *
 * @author ck
 * @date 2020/12/25
 */

open class BaseResponse(
    @field:SerializedName("status") val status: String,
    @field:SerializedName("message") val message: String,
)