package com.ck.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class CarDetailResponse(
    @field:SerializedName("data") val data: CarDetailBean,
)

@Parcelize
data class CarDetailBean(
    @field:SerializedName("acceleration") val acceleration: String,
    @field:SerializedName("activityPrice") val activityPrice: String,
    @field:SerializedName("brandId") val brandId: Int,
    @field:SerializedName("brandName") val brandName: String,
    @field:SerializedName("carName") val carName: String,
    @field:SerializedName("chair") val chair: String,
    @field:SerializedName("dayRent") val dayRent: String,
    @field:SerializedName("deposit") val deposit: String,
    @field:SerializedName("describe") val describe: String,
    @field:SerializedName("displacement") val displacement: String,
    @field:SerializedName("engine") val engine: String,
    @field:SerializedName("fuel") val fuel: String,
    @field:SerializedName("gearPosition") val gearPosition: String,
    @field:SerializedName("horsepower") val horsepower: String,
    @field:SerializedName("id") val id: String,
    @field:SerializedName("isActivity") val isActivity: Int,
    @field:SerializedName("isHot") val isHot: Int,
    @field:SerializedName("isCollection") val isCollection: Int,
    @field:SerializedName("leaseCount") val leaseCount: String,
    @field:SerializedName("openType") val openType: String,
    @field:SerializedName("recommendImg") val recommendImg: String,
    @field:SerializedName("speed") val speed: String,
    @field:SerializedName("imgList") val imgList: ArrayList<ImgListBean>,
) : Parcelable

@Parcelize
data class ImgListBean(
    @field:SerializedName("id") val id: String,
    @field:SerializedName("imgUrl") val imgUrl: String,
) : Parcelable

