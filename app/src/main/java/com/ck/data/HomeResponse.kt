package com.ck.data

import com.google.gson.annotations.SerializedName

/**
 *
 * @author ck
 * @date 2020/12/14
 */
data class HomeResponse(
    @field:SerializedName("data") val data: ArrayList<HomeBean>,
    @field:SerializedName("countSize") val countSize: Int
)



