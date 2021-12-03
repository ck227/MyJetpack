package com.ck.ui

import com.ck.data.ChargeHistoryBean

class ChargeHistoryBind(chargeHistoryBean: ChargeHistoryBean) {

    private val _price = chargeHistoryBean.rechargePrice
    private val _time = chargeHistoryBean.saveTime

    val price get() = _price
    val time get() = _time
}