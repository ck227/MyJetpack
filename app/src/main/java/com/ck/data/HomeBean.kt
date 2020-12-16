package com.ck.data

import com.google.gson.annotations.SerializedName


/**
 *
 * @author ck
 * @date 2020/12/15
 */
data class HomeBean(
    @field:SerializedName("id") val id: String,
    @field:SerializedName("informationImgApp") val informationImgApp: String
)
