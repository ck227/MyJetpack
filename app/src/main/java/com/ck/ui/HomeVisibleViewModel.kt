package com.ck.ui

import android.view.View

class HomeVisibleViewModel(boolean: Boolean) {

    private val isVisibleValue = boolean

    val isVisible get() = if (isVisibleValue) View.VISIBLE else View.GONE


}