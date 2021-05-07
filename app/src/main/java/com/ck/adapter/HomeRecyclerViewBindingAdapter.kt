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
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .placeholder(R.mipmap.default_icon)
            .error(R.mipmap.menu0_checked)
            .into(view)
    }
}

@BindingAdapter("imageFromResource")
fun bindImageFromResource(view: ImageView, imageResource: Int?) {
    Glide.with(view.context)
        .load(imageResource)
        .transition(DrawableTransitionOptions.withCrossFade())
        .placeholder(R.mipmap.home_title_im_msg_black)
        .error(R.mipmap.menu0_checked)
        .into(view)
}