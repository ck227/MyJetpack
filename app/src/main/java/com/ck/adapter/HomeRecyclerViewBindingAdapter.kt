package com.ck.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.ck.myjetpack.R


/**
 *
 * @author ck
 * @date 2020/12/17
 */

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2159135328,2589525587&fm=26&gp=0.jpg")
            .transition(DrawableTransitionOptions.withCrossFade())
            .placeholder(R.mipmap.home_title_im_msg_black)
            .error(R.mipmap.home_title_im_msg_black)
            .into(view)
    }
}