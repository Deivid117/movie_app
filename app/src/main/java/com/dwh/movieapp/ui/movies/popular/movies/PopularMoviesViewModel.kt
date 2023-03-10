package com.dwh.movieapp.ui.movies.popular.movies

import android.app.Application
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dwh.domain.models.entities.MovieEntinty
import com.dwh.domain.models.entities.TrendEntity
import com.dwh.domain.models.entities.toDatabase
import com.dwh.domain.models.get.movies.popular.upcoming.Movies
import com.dwh.movieapp.repository.LocalMovieRepository
import com.dwh.movieapp.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mx.com.satoritech.web.NetworkResult
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
@HiltViewModel
class PopularMoviesViewModel@Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val localMovieRepository: LocalMovieRepository,
    application: Application
): AndroidViewModel(application) {

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _movies = mutableStateOf(Movies())
    val movies: MutableState<Movies> = _movies

    fun getPopularMovies() = viewModelScope.launch {
        moviesRepository.getPopularMovies().collect {
            when(it){
                is NetworkResult.Success -> {
                    _isLoading.value = false
                    it.data?.let {  movies ->
                        _movies.value = movies
                        localMovieRepository.insert(movies.results.map { movieResults ->  movieResults.toDatabase() })
                    }
                }
                is NetworkResult.Loading -> {
                    _isLoading.value = true
                }
                is NetworkResult.Error -> {
                    _isLoading.value = false
                    Toast.makeText(getApplication(), it.message?:"", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private val _movieListLocal = MutableLiveData<List<MovieEntinty>>(listOf())
    val movieListLocal: LiveData<List<MovieEntinty>> = _movieListLocal

    fun getPopularMoviesDataBase() = viewModelScope.launch {
        _movieListLocal.postValue(localMovieRepository.getAllMovies())
    }
}