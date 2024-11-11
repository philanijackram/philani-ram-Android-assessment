package com.glucode.about_you.util

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class DataBindingAdapterUtil {

    companion object {
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(view: ImageView, profileImage: Uri?) {
            Glide.with(view.context).load(profileImage).into(view)
        }
    }
}