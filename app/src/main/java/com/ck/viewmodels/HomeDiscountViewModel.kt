package com.ck.viewmodels

import com.ck.data.CarBean


class HomeDiscountViewModel(carBean: CarBean) {

    private val imgUrl = checkNotNull(carBean.discountImgApp)
    private val carName = checkNotNull(carBean.brandName + " " + carBean.carName)
    private val activityPrice = checkNotNull(carBean.activityPrice)
    private val dayRent = checkNotNull(carBean.dayRent)
    
    val imageUrl
        get() = imgUrl

    val name
        get() = carName

    val price
        get() = activityPrice

    val originPrice
        get() =
            dayRent

}