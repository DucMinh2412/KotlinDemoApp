package com.example.kotlindemoapp.utils

import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.kotlindemoapp.R

class DataBindingUtils {

    companion object {
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(imageView: ImageView, url: String) {
            if (!url.isNullOrEmpty()) {
                Glide.with(imageView.context).load(url).into(imageView)
            }
        }
    }
}