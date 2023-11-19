package com.ikapurwanti.movie.di

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.ikapurwanti.movie.data.network.api.datasource.MovieDataSource
import com.ikapurwanti.movie.data.network.api.datasource.MovieDataSourceImpl
import com.ikapurwanti.movie.data.network.api.service.MovieService
import com.ikapurwanti.movie.data.repository.MovieRepository
import com.ikapurwanti.movie.data.repository.MovieRepositoryIml
import com.ikapurwanti.movie.presentation.feature.detail.DetailViewModel
import com.ikapurwanti.movie.presentation.feature.home.HomeFragment
import com.ikapurwanti.movie.presentation.feature.home.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

object AppModules {

    private val networkModule = module {
        single { ChuckerInterceptor(androidContext()) }
        single { MovieService.invoke(get()) }
    }

    private val dataSourceModule = module {
        single<MovieDataSource> { MovieDataSourceImpl(get()) }
    }

    private val repositoryModule = module {
        single<MovieRepository> { MovieRepositoryIml(get()) }
    }

    private val viewModelModule = module {
        viewModelOf(::HomeViewModel)
        viewModel { param -> DetailViewModel(param.get(), get()) }
    }

    val modules: List<Module> = listOf(
        networkModule,
        dataSourceModule,
        repositoryModule,
        viewModelModule
    )
}
