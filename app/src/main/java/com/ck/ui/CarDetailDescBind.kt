package com.ck.ui

import com.ck.data.CarDetailBean

class CarDetailDescBind(carDetailBean: CarDetailBean) {

    private val fuelValue = carDetailBean.fuel
    private val accelerationValue = "百公里加速 | " + carDetailBean.acceleration + "s"
    private val engineValue = "发动机 | " + carDetailBean.engine
    private val openTypeValue = carDetailBean.openType
    private val speedValue = "最高时速 | " + carDetailBean.speed + "km/h"
    private val chairValue = "座椅 | " + carDetailBean.chair + "个"
    private val horsePowerValue = "最大马力 | " + carDetailBean.horsepower + "Ps"
    private val displacementValue = "排量 | " + carDetailBean.displacement
    private val gearPositionValue = carDetailBean.gearPosition


    val fuel get() = fuelValue
    val acceleration get() = accelerationValue
    val engine get() = engineValue
    val openType get() = openTypeValue
    val speed get() = speedValue
    val chair get() = chairValue
    val horsepower get() = horsePowerValue
    val displacement get() = displacementValue
    val gearPosition get() = gearPositionValue

}