package com.ck.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class MsgResponse(
    @field:SerializedName("data") val data: ArrayList<MsgBean>,
    @field:SerializedName("countSize") val countSize: Int
)

@Parcelize
data class MsgBean(
    @field:SerializedName("id") val id: String,
    @field:SerializedName("informationCount") val informationCount: String,
    @field:SerializedName("saveTime") val saveTime: String,
    @field:SerializedName("title") val title: String,
) : Parcelable

