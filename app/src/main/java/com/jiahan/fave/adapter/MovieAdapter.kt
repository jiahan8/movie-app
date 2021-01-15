package com.jiahan.fave.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jiahan.fave.database.Movie
import com.jiahan.fave.databinding.MovieItemBinding
import com.jiahan.fave.view.MainFragmentDirections

class MovieAdapter(private val movieList: List<Movie>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MessageViewHolder( MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false) )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        setContentData(holder, position)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    private fun setContentData(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MessageViewHolder).bind(movieList[position])
    }

    class MessageViewHolder(private val movieItemBinding: MovieItemBinding) : RecyclerView.ViewHolder(movieItemBinding.root) {
        init {
            movieItemBinding.setMovieCallback {
                val action = MainFragmentDirections.actionMainToDetail(movieItemBinding.movie!!)
                it.findNavController().navigate(action)
            }
        }
        fun bind(movieData: Movie) {
            movieItemBinding.movie = movieData
        }
    }

    class RecyclerViewDiffUtil(private var oldList: List<Movie>, private var newList: List<Movie>) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldList.size
        }
        override fun getNewListSize(): Int {
            return newList.size
        }
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }
        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }
    }


}
