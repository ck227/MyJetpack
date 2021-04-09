package com.ck.data

import com.google.gson.annotations.SerializedName


/**
 *
 * @author ck
 * @date 2020/12/25
 */

open class BaseResponse(
    @field:SerializedName("rtn_code") val rtn_code: String,
    @field:SerializedName("rtn_ext") val rtn_ext: String,
    @field:SerializedName("rtn_ftype") val rtn_ftype: String,
    @field:SerializedName("rtn_msg") val rtn_msg: String,
    @field:SerializedName("rtn_tip") val rtn_tip: String
)