package com.jiahan.fave.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.jiahan.fave.databinding.MovieItemBinding
import com.jiahan.fave.view.MainFragmentDirections
import com.jiahan.fave.database.DatabaseMovie

class MovieAdapter(private val movieList: List<DatabaseMovie>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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

//    companion object {
//        private val diffCallback = object : DiffUtil.ItemCallback<Image>() {
//            override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean =
//                oldItem.id == newItem.id
//            override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean =
//                oldItem.id == newItem.id
//        }
//    }
    class MessageViewHolder(private val movieItemBinding: MovieItemBinding) : RecyclerView.ViewHolder(movieItemBinding.root) {
        init {
            movieItemBinding.setMovieCallback {
                val action = MainFragmentDirections.actionMainToDetail(movieItemBinding.movie!!)
                it.findNavController().navigate(action)
            }
        }
        fun bind(movieData: DatabaseMovie) {
            movieItemBinding.movie = movieData
        }
    }

}