package com.omni.core.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.omni.core.R

@BindingAdapter("imageUrl")
fun ImageView.bindImageUrl(imgUrl: String?) {
    imgUrl?.let {
        val requestOptions = RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
        Glide.with(context).asDrawable()
            .load(imgUrl)
            .apply(requestOptions)
            .transform(
                CenterCrop(),
                RoundedCorners(resources.getDimension(R.dimen.corner_radius_5).toInt())
            )
            .placeholder(R.drawable.loading_animation)
            .error(R.drawable.ic_broken_image)
            .transition(DrawableTransitionOptions.withCrossFade()).into(this)
    }
}
