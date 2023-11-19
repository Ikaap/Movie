package com.ikapurwanti.movie.presentation.feature.detail

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import com.ikapurwanti.movie.databinding.ActivityDetailBinding
import com.ikapurwanti.movie.model.VideoViewParam
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.net.URL

class DetailActivity : AppCompatActivity() {

    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    private val viewModel: DetailViewModel by viewModel {
        parametersOf(intent.extras ?: bundleOf())
    }

    private var exoPlayer : ExoPlayer? = null
    private var playbackPosition = 0L
    private var playWhenReady = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        showDetailMenu(viewModel.movie)

        preparePlayer(viewModel.movie)
    }

    @SuppressLint("UnsafeOptInUsageError")
    private fun preparePlayer(movie: VideoViewParam?) {
        exoPlayer = ExoPlayer.Builder(this).build()
        exoPlayer?.playWhenReady = true
        binding.playerView.player = exoPlayer

        val mediaItem = movie?.let { androidx.media3.common.MediaItem.fromUri(it.source) }

        // menggunakan progressiveMediaSource karena untuk file biasa
        val mediaSourceFactory = mediaItem?.let {
            ProgressiveMediaSource.Factory (
                DefaultDataSource.Factory(this))
                .createMediaSource(it)
        }

        if (mediaItem != null) {
            exoPlayer?.setMediaItem(mediaItem)
        }
        if (mediaSourceFactory != null) {
            exoPlayer?.setMediaSource(mediaSourceFactory)
        }
        exoPlayer?.seekTo(playbackPosition)
        exoPlayer?.playWhenReady = playWhenReady
        exoPlayer?.prepare()
    }

    private fun releasePlayer(){
        exoPlayer?.let { player ->
            playbackPosition = player.currentPosition
            playWhenReady = player.playWhenReady
            player.release()
            exoPlayer = null
        }
    }

    override fun onStop() {
        super.onStop()
        releasePlayer()
        preparePlayer(viewModel.movie)
    }

    override fun onPause() {
        super.onPause()
        releasePlayer()
        preparePlayer(viewModel.movie)
    }

    private fun showDetailMenu(movie: VideoViewParam?) {
        movie?.let {
            binding.tvMovieTitle.text = movie.title
            binding.tvMovieDesc.text = movie.description
        }

    }

    companion object {
        const val EXTRA_MOVIE = "EXTRA_MOVIE"
        fun startActivity(context: Context, movie: VideoViewParam) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRA_MOVIE, movie)
            context.startActivity(intent)
        }
    }
}