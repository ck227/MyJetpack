package com.ck.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


/**
 *
 * @author ck
 * @date 2021/5/25
 */

data class CarResponse(
    @field:SerializedName("data") val data: ArrayList<CarBean>,
    @field:SerializedName("params") val params: DisCountParams,
    @field:SerializedName("countSize") val countSize: Int
)

@Parcelize
data class CarBean(
    @field:SerializedName("activityPrice") val activityPrice: String,
    @field:SerializedName("brandId") val brandId: String,
    @field:SerializedName("brandName") val brandName: String,
    @field:SerializedName("carImgPc") val carImgPc: String,
    @field:SerializedName("carName") val carName: String,
    @field:SerializedName("collectionImgApp") val collectionImgApp: String,
    @field:SerializedName("createTime") val createTime: String,
    @field:SerializedName("dayRent") val dayRent: String,
    @field:SerializedName("deposit") val deposit: String,
    @field:SerializedName("describe") val describe: String,
    @field:SerializedName("discountImgApp") val discountImgApp: String,
    @field:SerializedName("discountImgPc") val discountImgPc: String,
    @field:SerializedName("id") val id: String,
    @field:SerializedName("isActivity") val isActivity: String,
    @field:SerializedName("isHot") val isHot: String,
    @field:SerializedName("ordCarImgApp") val ordCarImgApp: String,
    @field:SerializedName("recommendImg") val recommendImg: String,
    @field:SerializedName("recommendImgApp") val recommendImgApp: String
) : Parcelable

data class DisCountParams(
    @field:SerializedName("differTime") val differTime: String,
    @field:SerializedName("endTime") val endTime: String,
    @field:SerializedName("id") val id: String,
    @field:SerializedName("isValid") val isValid: String,
    @field:SerializedName("paramsKey") val paramsKey: String,
    @field:SerializedName("paramsValue") val paramsValue: String,
    @field:SerializedName("saveTime") val saveTime: String,
    @field:SerializedName("startTime") val startTime: String
)