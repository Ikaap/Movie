package com.ikapurwanti.movie.data.network.api.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.ikapurwanti.movie.model.CategoryViewParam
import com.ikapurwanti.movie.model.VideoViewParam

@Keep
data class Category(
    @SerializedName("name")
    val name: String,
    @SerializedName("videos")
    val videos: List<Video>
)


//fun Category.toCategory() = CategoryViewParam(
//    name = this.name,
//    videos = this.videos
//)
//
//fun Collection<Category>.toCategoryList() = this.map {
//    it.toCategory()
//}



