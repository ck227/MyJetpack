package com.ck.data

import com.google.gson.annotations.SerializedName

class LoginResponse(
    @field:SerializedName("status") val status: String,
    @field:SerializedName("message") val message: String,
    @field:SerializedName("data") val data: LoginBean
)


data class LoginBean(
    @field:SerializedName("accountBalance") val accountBalance: String,
    @field:SerializedName("autograph") val autograph: String,
    @field:SerializedName("headImg") val headImg: String,
    @field:SerializedName("id") val id: String,
    @field:SerializedName("loginName") val loginName: String,
    @field:SerializedName("memberBalance") val memberBalance: String,
    @field:SerializedName("nickName") val nickName: String,
    @field:SerializedName("pointCount") val pointCount: String,
    @field:SerializedName("sex") val sex: String,
    @field:SerializedName("userType") val userType: String,
    @field:SerializedName("userTypeId") val userTypeId: String,
)