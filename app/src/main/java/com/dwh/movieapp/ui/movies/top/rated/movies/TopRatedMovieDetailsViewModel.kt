package com.dwh.movieapp.ui.movies.top.rated.movies

import android.app.Application
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.dwh.domain.models.entities.TopRatedMoviesEntity
import com.dwh.domain.models.get.movies.details.MoviesDetails
import com.dwh.movieapp.repository.LocalMovieRepository
import com.dwh.movieapp.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mx.com.satoritech.web.NetworkResult
import javax.inject.Inject

@HiltViewModel
class TopRatedMovieDetailsViewModel@Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val localMoviesRepository: LocalMovieRepository,
    application: Application
): AndroidViewModel(application) {

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _topRatedMovieDetails = mutableStateOf(MoviesDetails())
    val topRatedMovieDetails: MutableState<MoviesDetails> = _topRatedMovieDetails

    fun getTopRatedMovieDetails(movieId: Long) = viewModelScope.launch {
        moviesRepository.getMovieDetails(movieId).collect {
            when(it){
                is NetworkResult.Success -> {
                    _isLoading.value = false
                    it.data?.let {  details ->
                        _topRatedMovieDetails.value = details
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

    private val _topRatedMovieDetialsLocal = mutableStateOf(TopRatedMoviesEntity())
    val topRatedMovieDetialsLocal: MutableState<TopRatedMoviesEntity> = _topRatedMovieDetialsLocal

    fun getTopRatedMovieDetailsDataBase(movieId: Int) = viewModelScope.launch {
        _topRatedMovieDetialsLocal.value = localMoviesRepository.findTopRatedMovieById(movieId)
    }
}
