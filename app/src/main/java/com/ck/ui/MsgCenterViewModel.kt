package com.ck.ui

import com.ck.data.MsgBean

class MsgCenterViewModel(msgBean: MsgBean) {

    val _id = msgBean.id

    val _title = msgBean.title

    val _saveTime = msgBean.saveTime
}