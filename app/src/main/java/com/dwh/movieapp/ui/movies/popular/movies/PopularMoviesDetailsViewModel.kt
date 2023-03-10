package com.dwh.movieapp.ui.movies.popular.movies

import android.app.Application
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.dwh.domain.models.entities.MovieEntinty
import com.dwh.domain.models.get.movies.details.MoviesDetails
import com.dwh.movieapp.repository.LocalMovieRepository
import com.dwh.movieapp.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mx.com.satoritech.web.NetworkResult
import javax.inject.Inject

@HiltViewModel
class PopularMoviesDetailsViewModel@Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val localMovieRepository: LocalMovieRepository,
    application: Application
): AndroidViewModel(application) {

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _movieDetails = mutableStateOf(MoviesDetails())
    val movieDetails: MutableState<MoviesDetails> = _movieDetails

    fun getMovieDetails(movieId: Long) = viewModelScope.launch {
        moviesRepository.getMovieDetails(movieId).collect {
            when(it){
                is NetworkResult.Success -> {
                    _isLoading.value = false
                    it.data?.let {  details ->
                        _movieDetails.value = details
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

    private val _movieDetailsLocal = mutableStateOf(MovieEntinty())
    val movieDetailsLocal: MutableState<MovieEntinty> = _movieDetailsLocal

    fun getMovieDetailsDataBase(movieId: Int) = viewModelScope.launch {
        _movieDetailsLocal.value = localMovieRepository.findMovieById(movieId)
    }
}