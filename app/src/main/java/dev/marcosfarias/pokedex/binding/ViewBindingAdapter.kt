package dev.marcosfarias.pokedex.binding

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.appbar.CollapsingToolbarLayout
import dev.marcosfarias.pokedex.R

@BindingAdapter("loadImage")
fun setLoadImageUrl(iv: ImageView, url: String?) {
    url ?: return

    Glide.with(iv.context)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(android.R.color.transparent)
        .into(iv)
}

@BindingAdapter(value = ["malePercentage", "femalePercentage"])
fun setPokemonGenderPercentage(tv: TextView, male: String?, female: String?) {

    if (male.isNullOrEmpty() && female.isNullOrEmpty()) {
        tv.text = tv.context.getString(R.string.default_pokemon_gender)
    } else {
        tv.text = String.format(
            tv.context.getString(R.string.format_pokemon_gender),
            male ?: "0%",
            female ?: "0%"
        )
    }
}