package com.dwh.movieapp.ui.movies.top.rated.movies

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
import com.dwh.domain.models.entities.TopRatedMoviesEntity
import com.dwh.domain.models.entities.toTopRatedMoviesDB
import com.dwh.domain.models.get.movies.popular.upcoming.Movies
import com.dwh.movieapp.repository.LocalMovieRepository
import com.dwh.movieapp.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mx.com.satoritech.web.NetworkResult
import javax.inject.Inject

@HiltViewModel
class TopRatedMoviesViewModel@Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val localMoviesRepository: LocalMovieRepository,
    application: Application
): AndroidViewModel(application) {

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _topRatedMovies = mutableStateOf(Movies())
    val topRatedMovies: MutableState<Movies> = _topRatedMovies

    fun getTopRatedMovies() = viewModelScope.launch {
        moviesRepository.getTopRatedMovies().collect {
            when(it){
                is NetworkResult.Success -> {
                    _isLoading.value = false
                    it.data?.let {  movies ->
                        _topRatedMovies.value = movies
                        localMoviesRepository.insertTopRatedMovies(movies.results.map { moviesResult -> moviesResult.toTopRatedMoviesDB() })
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

    private val _topRatedMoviesListLocal = MutableLiveData<List<TopRatedMoviesEntity>>(listOf())
    val topRatedMoviesListLocal: LiveData<List<TopRatedMoviesEntity>> = _topRatedMoviesListLocal

    fun getTopRatedMoviesDataBase() = viewModelScope.launch {
        _topRatedMoviesListLocal.postValue(localMoviesRepository.getAllTopRatedMovies())
    }
}