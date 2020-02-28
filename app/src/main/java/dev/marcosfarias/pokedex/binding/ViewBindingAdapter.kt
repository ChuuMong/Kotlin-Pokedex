package dev.marcosfarias.pokedex.binding

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.appbar.CollapsingToolbarLayout

@BindingAdapter("loadImage")
fun setLoadImageUrl(iv: ImageView, url: String?) {
    url ?: return

    Glide.with(iv.context)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(android.R.color.transparent)
        .into(iv)
}