package com.example.appmovies.model.model.interf

import com.example.appmovies.model.model.constantes.ApiConst.API_KEY
import com.example.appmovies.model.model.constantes.LanguageConst.LANGUAGE_MOVIE
import com.example.appmovies.model.model.model.MovieListModel
import com.example.appmovies.model.model.model.MovieModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("3/movie/popular")
    fun listPopular(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE_MOVIE,
        @Query("page") page: Int = 1,
    ): Call<MovieListModel>

    @GET("3/movie/{idMovieURL}")
    fun getMovieById(
        @Path("idMovieURL") id: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE_MOVIE,
    ): Call<MovieModel>

    @GET("3/movie/upcoming")
    fun upcomingList(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE_MOVIE,
        @Query("page") page: Int = 1,
    ): Call<MovieListModel>

}