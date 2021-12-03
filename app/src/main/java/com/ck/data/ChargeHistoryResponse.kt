package com.ck.data

import com.google.gson.annotations.SerializedName

data class ChargeHistoryResponse(
    @field:SerializedName("status") val status: String,
    @field:SerializedName("message") val message: String,
    @field:SerializedName("accountBalance") val accountBalance: String,
    @field:SerializedName("memberBalance") val memberBalance: String,
    @field:SerializedName("data") val data: ArrayList<ChargeHistoryBean>,
)

data class ChargeHistoryBean(
    @field:SerializedName("id") val id: String,
    @field:SerializedName("rechargeOrderSn") val rechargeOrderSn: String,
    @field:SerializedName("rechargePrice") val rechargePrice: String,
    @field:SerializedName("rechargeType") val rechargeType: String,
    @field:SerializedName("saveTime") val saveTime: String,
)
