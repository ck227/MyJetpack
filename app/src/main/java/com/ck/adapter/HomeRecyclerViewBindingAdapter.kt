package com.ck.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.ck.myjetpack.R
import android.graphics.Paint
import android.view.View


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

@BindingAdapter("carPriceText")
fun bindCartPriceText(textView: TextView, price: String?) {
    val resources = textView.context.resources
    val quantityString = resources.getString(
        R.string.price_text,
        price
    )
    textView.text = quantityString
}

@BindingAdapter("carOriginPriceText")
fun bindCartOriginPriceText(textView: TextView, price: String?) {
    val resources = textView.context.resources
    val quantityString = resources.getString(
        R.string.price_text,
        price
    )
    textView.text = quantityString
    textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
}

//@BindingAdapter("setViewVisible")
//fun bindSetViewVisible(view: View, boolean: Boolean) {
//    view.visibility = if (boolean) View.VISIBLE else View.GONE
//}