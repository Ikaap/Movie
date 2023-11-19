package com.ikapurwanti.movie.presentation.feature.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.ikapurwanti.movie.databinding.ItemMovieBinding
import com.ikapurwanti.movie.model.VideoViewParam

class MovieListAdapter(
    private val onClickListener: (VideoViewParam) -> Unit
): RecyclerView.Adapter<MovieItemListViewHolder>() {

    private val dataDiffer =
        AsyncListDiffer(this, object : DiffUtil.ItemCallback<VideoViewParam>() {
            override fun areItemsTheSame(
                oldItem: VideoViewParam,
                newItem: VideoViewParam
            ): Boolean {
                return oldItem.source == newItem.source

            }

            override fun areContentsTheSame(
                oldItem: VideoViewParam,
                newItem: VideoViewParam
            ): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

        })


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemListViewHolder {
        return MovieItemListViewHolder(
            binding = ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClickListener
        )
    }

    override fun getItemCount(): Int = dataDiffer.currentList.size

    override fun onBindViewHolder(holder: MovieItemListViewHolder, position: Int) {
        holder.bind(dataDiffer.currentList[position])
    }

    fun setData(data: List<VideoViewParam>) {
        dataDiffer.submitList(data)
    }

    fun refreshList() {
        notifyItemRangeChanged(0, dataDiffer.currentList.size)
    }
}


class MovieItemListViewHolder(
    private val binding: ItemMovieBinding,
    private val onClickListener: (VideoViewParam) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(video: VideoViewParam) {

        with(binding) {
            ivMovieThumb.load(video.thumb)
            tvMovieTitle.text = video.title
        }
        binding.root.setOnClickListener {
            onClickListener(video)
        }
    }
}