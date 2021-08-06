package com.example.appmovies.model.view.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appmovies.databinding.MovieListBinding
import com.example.appmovies.model.model.repository.MovieRepository
import com.example.appmovies.model.view.constantes.MovieIdConst.idMovie
import com.example.appmovies.model.view.adapter.MovieAdapter

class MovieListActivity : AppCompatActivity() {
    private lateinit var binding: MovieListBinding

    companion object {
        const val UPCOMING = 0
        const val POPULAR = 1
        const val TYPE_KEY = "com.example.appmovies.model.view.activity.MovieListView.TYPE_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MovieListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapterMovie = MovieAdapter { id ->
            val intent = Intent(this, MovieDetailActivity::class.java)
            intent.putExtra(idMovie, id)
            startActivity(intent)
        }

        binding.backButton.setOnClickListener {
            onBackPressed()
        }

        binding.moviesRecyclerViewList.adapter = adapterMovie
        val tipoDeLista = intent.getIntExtra(TYPE_KEY, -1)
        when (tipoDeLista) {
            UPCOMING -> {
                MovieRepository.getUpcoming(this, { list -> adapterMovie.updateList(list) }, 1)
            }
            POPULAR -> {
                MovieRepository.getPopular(this, { list -> adapterMovie.updateList(list) }, 1)
            }
            else -> {
                Toast.makeText(this, "ERRO", Toast.LENGTH_LONG).show()
            }
        }
    }
}