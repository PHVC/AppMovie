package com.example.appmovies.model.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appmovies.databinding.ActivityMainBinding
import com.example.appmovies.model.view.activity.MovieListActivity.Companion.POPULAR
import com.example.appmovies.model.view.activity.MovieListActivity.Companion.TYPE_KEY
import com.example.appmovies.model.view.activity.MovieListActivity.Companion.UPCOMING

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.popularButton.setOnClickListener {
            val mudaTela = Intent(this, MovieListActivity::class.java)
            mudaTela.putExtra(TYPE_KEY, POPULAR)
            startActivity(mudaTela)
        }

        binding.upcomingButton.setOnClickListener {
            val mudaTela = Intent(this, MovieListActivity::class.java)
            mudaTela.putExtra(TYPE_KEY, UPCOMING)
            startActivity(mudaTela)
        }
    }
}