package com.example.appmovies.model.model.repository

import android.content.Context
import android.widget.Toast
import com.example.appmovies.model.model.model.MovieListModel
import com.example.appmovies.model.model.model.MovieModel
import com.example.appmovies.model.model.interf.MovieApi
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieRepository {
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://api.themoviedb.org/")
        .build()

    val moviesApi: MovieApi = retrofit.create(MovieApi::class.java)

    fun getPopular(context: Context, callback: (List<MovieModel>) -> Unit, page: Int = 1) {
        CoroutineScope(GlobalScope.coroutineContext).launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                val callApi = moviesApi.listPopular(page = page)

                callApi.enqueue(object : Callback<MovieListModel> {
                    override fun onResponse(
                        call: Call<MovieListModel>,
                        response: Response<MovieListModel>,
                    ) {
                        callback(response.body()?.results ?: mutableListOf())
                    }

                    override fun onFailure(call: Call<MovieListModel>, t: Throwable) {
                        Toast.makeText(context, "Erro na busca.", Toast.LENGTH_LONG).show()
                    }
                })
            }
        }
    }

    fun getMovie(callback: (MovieModel) -> Unit, id: Int) {
        CoroutineScope(GlobalScope.coroutineContext).launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                val callApi = moviesApi.getMovieById(id = id)

                callApi.enqueue(object : Callback<MovieModel> {
                    override fun onResponse(
                        call: Call<MovieModel>,
                        response: Response<MovieModel>,
                    ) {
                        response.body()?.let { movie -> callback(movie) }
                    }

                    override fun onFailure(call: Call<MovieModel>, t: Throwable) {
                    }
                })
            }
        }
    }

    fun getUpcoming(context: Context, callback: (List<MovieModel>) -> Unit, page: Int = 1) {
        CoroutineScope(GlobalScope.coroutineContext).launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                val callApi = moviesApi.upcomingList(page = page)

                callApi.enqueue(object : Callback<MovieListModel> {
                    override fun onResponse(
                        call: Call<MovieListModel>,
                        response: Response<MovieListModel>,
                    ) {
                        callback(response.body()?.results ?: mutableListOf())
                    }

                    override fun onFailure(call: Call<MovieListModel>, t: Throwable) {
                        Toast.makeText(context, "Erro na busca.", Toast.LENGTH_LONG).show()
                    }
                })
            }
        }
    }
}