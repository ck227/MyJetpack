package com.ck.ui

import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import com.ck.data.CarDetailBean

class CarDetailBind(carDetailBean: CarDetailBean) {

    private val nameValue = carDetailBean.brandName + "  " + carDetailBean.carName
    private val originVisibleValue = carDetailBean.isActivity == 1
    private val originPriceValue = carDetailBean.dayRent
    private val priceValue =
        if (originVisibleValue) carDetailBean.activityPrice else carDetailBean.dayRent
    private val desValue = carDetailBean.describe

    val name get() = nameValue
    val originVisible get() = if (originVisibleValue) VISIBLE else INVISIBLE
    val originPrice = "¥" + originPriceValue + "元/天"
    val price = "¥" + priceValue + "元/天"
    val des = desValue

}