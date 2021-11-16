package com.ck.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ck.data.ImgListBean
import com.ck.widget.CarDetailBannerView

class CarDetailBannerAdapter(data: List<ImgListBean>) : RecyclerView.Adapter<CardViewHolder>() {

    private val hi: List<ImgListBean> = data

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder(CarDetailBannerView(LayoutInflater.from(parent.context), parent))
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(hi[position])
    }

    override fun getItemCount(): Int {
        return hi.size
    }
}

class CardViewHolder internal constructor(private val carDetailBannerView: CarDetailBannerView) :
    RecyclerView.ViewHolder(carDetailBannerView.view) {
    internal fun bind(imgListBean: ImgListBean) {
        carDetailBannerView.bind(imgListBean)
    }
}