package com.example.bookrecommendation.utils

import android.text.TextUtils
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.bookrecommendation.R

@BindingAdapter("imgUrl")
fun setImageUrl(img: AppCompatImageView, url: String?) {
    if (!TextUtils.isEmpty(url)) {
        Glide.with(img.context).load(url).centerCrop()
            .placeholder(R.drawable.ic_errorimg).dontAnimate().into(img)
       /* img.load(url) {
            crossfade(false)
            placeholder(R.drawable.ic_errorimg)
            scale(Scale.FILL).error(R.drawable.ic_errorimg)
            transformations(RoundedCornersTransformation(25f))
        }*/
    } else {
        img.setImageResource(R.drawable.ic_errorimg)
    }
}


@BindingAdapter("setRating")
fun setRating(view: AppCompatTextView, txt:Double) {
    view.text = String.format("%.2f", txt)
}
