package com.glucode.about_you.util

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.glucode.about_you.R

class DataBindingAdapterUtil {

    companion object {
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(view: ImageView, profileImage: Uri?) {
            if(profileImage.toString().isNotEmpty()){
                Glide.with(view.context).load(profileImage).into(view)
            }else{
                view.setImageResource(R.drawable.ic_person_black)
            }
        }

        @BindingAdapter("android:src")
        fun setImageViewResource(imageView: ImageView, resource: Int) {
            imageView.setImageResource(resource);
        }
    }
}