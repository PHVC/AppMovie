package com.example.appmovies.model.view.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.appmovies.databinding.MovieDetailBinding
import com.example.appmovies.model.model.repository.MovieRepository
import com.example.appmovies.model.view.constantes.MovieIdConst.idMovie

class MovieDetailActivity : AppCompatActivity() {
    private lateinit var binding: MovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val filmId = intent.getIntExtra(idMovie, -1)

        binding.returnButton.setOnClickListener {
            onBackPressed()
        }

        MovieRepository.getMovie({ it ->
            binding.movieSynopsis.text = it.overview
            binding.titleMovie.text = it.title
            binding.movieDuration.text = it.runtime
            binding.ageGroupDetail.text = if (it.adult) "+18 " else "-18 "

            Glide.with(binding.root)
                .load("https://image.tmdb.org/t/p/w500${it.poster_path}")
                .into(binding.posterPathDetail)
        }, filmId)
    }

    fun inFavorite(view: View) {
        if (binding.favoriteButton.isChecked) {
            Toast.makeText(this, "Added to Favorite", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Removed from Favorite", Toast.LENGTH_SHORT).show()
        }
    }
}