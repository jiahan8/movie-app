package com.jiahan.fave.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.jiahan.fave.databinding.FragmentDetailBinding
import com.jiahan.fave.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment(){

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val detailFragmentArgs by navArgs<DetailFragmentArgs>()
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate( inflater, container,false)
        val view = binding.root

        binding.lifecycleOwner = viewLifecycleOwner
        binding.movie = detailFragmentArgs.movie

        // Get movie detail based on movie id
        viewModel.getMovieDetail( detailFragmentArgs.movie.movie_id )

        // Update view from live data
        viewModel.movieDetail.observe( viewLifecycleOwner, {
            binding.moviedetail = it
        })

        // Navigate to web view
        binding.button.setOnClickListener {
            val action = DetailFragmentDirections.actionDetailToWebview()
            NavHostFragment.findNavController(this).navigate(action)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}