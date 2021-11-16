package com.ck.widget

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.ck.adapter.bindImageFromUrl
import com.ck.data.ImgListBean
import com.ck.myjetpack.R

class CarDetailBannerView(layoutInflater: LayoutInflater, container: ViewGroup?) {

    private val imageView: ImageView
    val view: View = layoutInflater.inflate(R.layout.item_car_detail_banner, container, false)

    init {
        imageView = view.findViewById(R.id.iv_banner)
    }

    fun bind(imgListBean: ImgListBean) {
        bindImageFromUrl(imageView, imgListBean.imgUrl)
    }

}