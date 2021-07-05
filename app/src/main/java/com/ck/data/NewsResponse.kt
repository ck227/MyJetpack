package com.ck.data

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @field:SerializedName("data") val data: ArrayList<NewsBean>,
    @field:SerializedName("countSize") val countSize: Int
)

data class NewsBean(
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("informationCount") val informationCount: String,
    @field:SerializedName("informationImg") val informationImg: String,
    @field:SerializedName("informationImgApp") val informationImgApp: String,
    @field:SerializedName("informationType") val informationType: Int,
    @field:SerializedName("introduction") val introduction: String,
    @field:SerializedName("saveTime") val saveTime: String,
    @field:SerializedName("source") val source: String,
    @field:SerializedName("title") val title: String
)
