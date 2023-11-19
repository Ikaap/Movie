package com.ikapurwanti.movie.data.network.api.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class MovieResponse(
    @SerializedName("categories")
    val categories: List<Category>
)