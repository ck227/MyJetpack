package com.ck.viewmodels

import com.ck.data.CarBean

class CarListViewModel(carBean: CarBean) {

    private val imgUrl = checkNotNull(carBean.discountImgApp)
}