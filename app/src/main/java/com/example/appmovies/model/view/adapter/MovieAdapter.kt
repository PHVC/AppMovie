package com.example.appmovies.model.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appmovies.databinding.MovieItemBinding
import com.example.appmovies.model.model.model.MovieModel
import com.example.appmovies.model.view.holder.MovieViewHolder

class MovieAdapter(val movieClickListener: (Int) -> Unit) :
    RecyclerView.Adapter<MovieViewHolder>() {
    private val listMovies: MutableList<MovieModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieItemBinding.inflate(inflater, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = listMovies.get(index = position)

        holder.binding.titleMovieList.text = item.title
        holder.binding.movieRelevanceList.text = item.vote_average
        holder.binding.movieYearList.text = item.release_date

        Glide.with(holder.binding.root)
            .load("https://image.tmdb.org/t/p/w500${item.poster_path}")
            .into(holder.binding.moviePoster)

        holder.binding.painelMovieList.setOnClickListener {
            movieClickListener(item.id)
        }
    }

    override fun getItemCount(): Int {
        return listMovies.size
    }

    fun updateList(list: List<MovieModel>) {
        listMovies.addAll(list)
        notifyDataSetChanged()
    }

}