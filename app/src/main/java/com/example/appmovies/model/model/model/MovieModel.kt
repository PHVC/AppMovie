package com.example.appmovies.model.model.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_movie")
data class MovieModel(
    @ColumnInfo(name = "title")
    val title: String,
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "poster_path")
    val poster_path: String,
    @ColumnInfo(name = "overview")
    val overview: String,
    @ColumnInfo(name = "vote_average")
    val vote_average: String,
    @ColumnInfo(name = "runtime")
    val runtime: String,
    @ColumnInfo(name = "release_date")
    val release_date: String,
    @ColumnInfo(name = "adult")
    val adult: Boolean,
    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean = false,
)
