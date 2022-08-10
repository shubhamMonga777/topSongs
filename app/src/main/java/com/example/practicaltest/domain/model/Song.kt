package com.example.practicaltest.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Song(
    val title: String,
    val link: String,
    val id: Int? = null,
    val image: String,
    val artistName: String,
    val price: String,
    val category: String
) : Parcelable