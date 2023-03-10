package com.dwh.movieapp.ui.movies.upcoming.movies

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
import com.dwh.domain.models.entities.UpcomingMoviesEntity
import com.dwh.domain.models.entities.toDatabase
import com.dwh.domain.models.entities.toUpcomingMovieDB
import com.dwh.domain.models.get.movies.popular.upcoming.Movies
import com.dwh.movieapp.repository.LocalMovieRepository
import com.dwh.movieapp.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mx.com.satoritech.web.NetworkResult
import javax.inject.Inject

@HiltViewModel
class UpcomingMoviesViewModel@Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val localMovieRepository: LocalMovieRepository,
    application: Application
): AndroidViewModel(application) {

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _upcomingMovies = mutableStateOf(Movies())
    val upcomingMovies: MutableState<Movies> = _upcomingMovies

    fun getUpcomingMovies() = viewModelScope.launch {
        moviesRepository.getUpcomingMovies().collect {
            when(it) {
                is NetworkResult.Success -> {
                    _isLoading.value = false
                    it.data?.let {  movies ->
                        _upcomingMovies.value = movies
                        localMovieRepository.insertUpcomingMovies(movies.results.map { moviesResults -> moviesResults.toUpcomingMovieDB() })
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

    private val _upcomingMovieListLocal = MutableLiveData<List<UpcomingMoviesEntity>>(listOf())
    val upcomingMovieListLocal: LiveData<List<UpcomingMoviesEntity>> = _upcomingMovieListLocal

    fun getUpcomingMoviesDataBase() = viewModelScope.launch {
        _upcomingMovieListLocal.postValue(localMovieRepository.getAllUpcomingMovies())
    }
}