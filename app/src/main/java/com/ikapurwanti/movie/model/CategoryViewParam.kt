package com.ikapurwanti.movie.model

import com.google.gson.annotations.SerializedName
import com.ikapurwanti.movie.data.network.api.model.Video

data class CategoryViewParam(
    val name: String,
    val videos: List<Video>
)
