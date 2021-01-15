package com.jiahan.fave.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jiahan.fave.R
import com.jiahan.fave.adapter.MovieAdapter
import com.jiahan.fave.database.Movie
import com.jiahan.fave.databinding.FragmentMainBinding
import com.jiahan.fave.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()
    private var adapter: MovieAdapter? = null
    var userDataList: MutableList<Movie> = ArrayList()
    var layoutManager: LinearLayoutManager? = null
    var isLoading = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate( inflater, container, false )
        val view = binding.root

        layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerview.layoutManager = layoutManager
        adapter = MovieAdapter(userDataList)
//        adapter = MovieAdapter()

        binding.recyclerview.adapter = adapter

        val dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        val horizontalDivider = context?.let { ContextCompat.getDrawable(it, R.drawable.divider) }
        dividerItemDecoration.setDrawable(horizontalDivider!!)
        binding.recyclerview.addItemDecoration(dividerItemDecoration)

        binding.lifecycleOwner = viewLifecycleOwner

        // Get updated movies when refreshing the page
        binding.swlContent.setOnRefreshListener {
            viewModel.getMovie(1)
            binding.swlContent.isRefreshing = false
        }
        // Load more movies
        binding.recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if( dy > 0 ){
                    val visibleItemCount = layoutManager?.childCount
                    val totalItemCount = layoutManager?.itemCount
                    val pastVisiblesItems = layoutManager?.findFirstVisibleItemPosition()
                    if(isLoading){
//                        Log.e("abc", "loading" )
                        if( (visibleItemCount!!.plus(pastVisiblesItems!!)) >= totalItemCount!!.minus(3)){
                            isLoading = false
                            // determine page number based on size of movies loaded
                            viewModel.getMovie(userDataList.size.div(20) + 1)
                        }
                    }
                }
            }
        })

        // Update view from Room Live Data
        viewModel.movielist.observe(viewLifecycleOwner, { movies ->
            if( userDataList.size == 0 ){
                userDataList.clear()
                userDataList.addAll(movies)
                adapter?.notifyDataSetChanged()
            }else {
                // Use diffutil to improve efficiency
                val diffResult = DiffUtil.calculateDiff(MovieAdapter.RecyclerViewDiffUtil(userDataList, movies))
                userDataList.clear()
                userDataList.addAll(movies)
                diffResult.dispatchUpdatesTo(adapter!!)
            }
            isLoading = true
        })
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}