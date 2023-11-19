package com.ikapurwanti.movie.data.network.api.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.ikapurwanti.movie.model.VideoViewParam

@Keep
data class Video(
    @SerializedName("description")
    val description: String,
    @SerializedName("source")
    val source: String,
    @SerializedName("subtitle")
    val subtitle: String,
    @SerializedName("thumb")
    val thumb: String,
    @SerializedName("title")
    val title: String
)

fun Video.toVideo() = VideoViewParam(
    description = this.description,
    source = this.source,
    subtitle = this.subtitle,
    thumb = this.thumb,
    title = this.title
)

fun Collection<Video>.toVideoList() = this.map {
    it.toVideo()
}
