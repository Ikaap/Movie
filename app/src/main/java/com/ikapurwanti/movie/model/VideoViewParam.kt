package com.ikapurwanti.movie.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class VideoViewParam(
    val description: String,
    val source: String,
    val subtitle: String,
    val thumb: String,
    val title: String
): Parcelable
