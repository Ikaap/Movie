package com.ikapurwanti.movie.presentation.feature.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ikapurwanti.movie.R
import com.ikapurwanti.movie.databinding.ActivityMainBinding
import com.ikapurwanti.movie.model.VideoViewParam
import com.ikapurwanti.movie.presentation.feature.detail.DetailActivity
import com.ikapurwanti.movie.presentation.feature.home.adapter.MovieListAdapter

class MainActivity : AppCompatActivity() {
    private val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}