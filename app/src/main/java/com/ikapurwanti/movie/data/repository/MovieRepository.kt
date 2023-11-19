package com.ikapurwanti.movie.data.repository

import com.ikapurwanti.movie.data.network.api.datasource.MovieDataSource
import com.ikapurwanti.movie.data.network.api.model.toVideoList
import com.ikapurwanti.movie.model.VideoViewParam
import com.ikapurwanti.movie.utils.ResultWrapper
import com.ikapurwanti.movie.utils.proceedFlow
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovies(): Flow<ResultWrapper<List<VideoViewParam>>>
}

class MovieRepositoryIml(private val dataSource: MovieDataSource): MovieRepository{
    override suspend fun getMovies(): Flow<ResultWrapper<List<VideoViewParam>>> {
        return proceedFlow {
            dataSource.getMovies().categories[0].videos.toVideoList()
        }
    }
}