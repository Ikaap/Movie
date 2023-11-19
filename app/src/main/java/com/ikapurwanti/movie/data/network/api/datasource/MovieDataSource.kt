package com.ikapurwanti.movie.data.network.api.datasource

import com.ikapurwanti.movie.data.network.api.model.Category
import com.ikapurwanti.movie.data.network.api.model.MovieResponse
import com.ikapurwanti.movie.data.network.api.model.Video
import com.ikapurwanti.movie.data.network.api.service.MovieService

interface MovieDataSource {
    suspend fun getMovies(): MovieResponse
}

class MovieDataSourceImpl(private val service: MovieService): MovieDataSource{
    override suspend fun getMovies(): MovieResponse {
        return service.getMovies()
    }

}