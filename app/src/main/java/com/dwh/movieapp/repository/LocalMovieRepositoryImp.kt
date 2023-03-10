package com.dwh.movieapp.repository

import com.dwh.domain.models.entities.MovieEntinty
import com.dwh.domain.models.entities.TopRatedMoviesEntity
import com.dwh.domain.models.entities.UpcomingMoviesEntity
import mx.com.satoritech.database.DBMovieApp
import javax.inject.Inject

class LocalMovieRepositoryImp@Inject constructor(
    private val dbMovieApp: DBMovieApp
): LocalMovieRepository {

    override suspend fun insert(movieEntinty: List<MovieEntinty>) {
        return dbMovieApp.movieDao().insert(movieEntinty)
    }

    override suspend fun getAllMovies(): List<MovieEntinty> {
        return dbMovieApp.movieDao().getAllMovies()
    }

    override suspend fun findMovieById(idMovie: Int): MovieEntinty {
        return dbMovieApp.movieDao().findMovieById(idMovie)
    }

    override suspend fun insertUpcomingMovies(upcomingMoviesEntity: List<UpcomingMoviesEntity>) {
        return dbMovieApp.upcomingMovieDao().insert(upcomingMoviesEntity)
    }

    override suspend fun getAllUpcomingMovies(): List<UpcomingMoviesEntity> {
        return dbMovieApp.upcomingMovieDao().getAllUpcomingMovies()
    }

    override suspend fun insertTopRatedMovies(topRatedMoviesEntity: List<TopRatedMoviesEntity>) {
        return dbMovieApp.topRatedMovies().insertTopRatedMovies(topRatedMoviesEntity)
    }

    override suspend fun getAllTopRatedMovies(): List<TopRatedMoviesEntity> {
        return dbMovieApp.topRatedMovies().getAllTopRatedMovies()
    }

    override suspend fun findTopRatedMovieById(idMovie: Int): TopRatedMoviesEntity {
        return dbMovieApp.topRatedMovies().findTopRatedMovieById(idMovie)
    }
}