package com.example.practicaltest.util

import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.practicaltest.R

@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String?) {
    Glide.with(this).load(url).placeholder(R.color.color_grey)
        .error(R.color.color_grey).into(this)
}

@BindingAdapter(value = ["currency", "price"])
fun AppCompatTextView.setPrice(currency: String, price: String) {
    this.text = context.getString(R.string.price, currency, price)
}