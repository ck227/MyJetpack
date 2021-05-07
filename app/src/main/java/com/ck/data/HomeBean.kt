package com.ck.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


/**
 *
 * @author ck
 * @date 2020/12/15
 */
@Parcelize
data class HomeBean(
    @field:SerializedName("id") val id: String,
    @field:SerializedName("title") val title: String,
    @field:SerializedName("informationImgApp") val informationImgApp: String
) : Parcelable

