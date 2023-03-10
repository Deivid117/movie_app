package com.dwh.movieapp.repository

import com.dwh.domain.models.entities.MovieEntinty
import com.dwh.domain.models.entities.TopRatedMoviesEntity
import com.dwh.domain.models.entities.UpcomingMoviesEntity

interface LocalMovieRepository {
    //Get popular movies
    suspend fun insert(movieEntinty: List<MovieEntinty>)
    suspend fun getAllMovies(): List<MovieEntinty>
    //Get popular movie details
    suspend fun findMovieById(idMovie: Int): MovieEntinty
    //Get upcoming movies
    suspend fun insertUpcomingMovies(upcomingMoviesEntity: List<UpcomingMoviesEntity>)
    suspend fun getAllUpcomingMovies(): List<UpcomingMoviesEntity>
    //Get top rated movies
    suspend fun insertTopRatedMovies(topRatedMoviesEntity: List<TopRatedMoviesEntity>)
    suspend fun getAllTopRatedMovies(): List<TopRatedMoviesEntity>
    //Get top rated movie details
    suspend fun findTopRatedMovieById(idMovie: Int): TopRatedMoviesEntity
}