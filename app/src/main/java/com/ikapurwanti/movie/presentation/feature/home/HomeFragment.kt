package com.ikapurwanti.movie.presentation.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ikapurwanti.movie.databinding.FragmentHomeBinding
import com.ikapurwanti.movie.model.VideoViewParam
import com.ikapurwanti.movie.presentation.feature.detail.DetailActivity
import com.ikapurwanti.movie.presentation.feature.home.adapter.MovieListAdapter
import com.ikapurwanti.movie.utils.proceedWhen
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val adapterMovie: MovieListAdapter by lazy {
        MovieListAdapter{
            navigateToDetail(it)
        }
    }

    private fun navigateToDetail(movie: VideoViewParam) {
        DetailActivity.startActivity(requireContext(), movie)
    }


    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvListMovie()
        getData()
        observeData()
    }

    private fun observeData() {
        viewModel.movie.observe(viewLifecycleOwner){
            it.proceedWhen(
                doOnSuccess = {
                    binding.rvMovie.isVisible = true
                    binding.layoutState.root.isVisible = false
                    binding.layoutState.pbLoading.isVisible = false
                    binding.layoutState.tvError.isVisible = false
                    binding.rvMovie.apply {
                        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                        adapter = adapterMovie
                    }
                    it.payload?.let { data ->
                        adapterMovie.setData(data)
                    }
                },
                doOnLoading = {
                    binding.rvMovie.isVisible = false
                    binding.layoutState.root.isVisible = true
                    binding.layoutState.pbLoading.isVisible = true
                    binding.layoutState.tvError.isVisible = false
                },
                doOnError = {
                    binding.rvMovie.isVisible = false
                    binding.layoutState.root.isVisible = true
                    binding.layoutState.pbLoading.isVisible = false
                    binding.layoutState.tvError.isVisible = true
                    binding.layoutState.tvError.text = it.exception?.message
                },
                doOnEmpty = {
                    binding.rvMovie.isVisible = false
                    binding.layoutState.root.isVisible = true
                    binding.layoutState.pbLoading.isVisible = false
                    binding.layoutState.tvError.isVisible = true
                    binding.layoutState.tvError.text = "Movie not found"
                }
            )
        }
    }

    private fun getData() {
        viewModel.getMovie()
    }

    private fun rvListMovie() {
        binding.rvMovie.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapterMovie
            adapterMovie.refreshList()
        }
    }

}