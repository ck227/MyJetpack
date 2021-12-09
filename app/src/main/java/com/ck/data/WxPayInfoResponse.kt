package com.ck.data

import com.google.gson.annotations.SerializedName

data class WxPayInfoResponse(
    @field:SerializedName("status") val status: String,
    @field:SerializedName("message") val message: String,
    @field:SerializedName("appId") val appId: String,
    @field:SerializedName("mchid") val mchid: String,
    @field:SerializedName("nonceStr") val nonceStr: String,
    @field:SerializedName("paySign") val paySign: String,
    @field:SerializedName("prepay_id") val prepay_id: String,
    @field:SerializedName("sign") val sign: String,
    @field:SerializedName("timeStamp") val timeStamp: String,
)

