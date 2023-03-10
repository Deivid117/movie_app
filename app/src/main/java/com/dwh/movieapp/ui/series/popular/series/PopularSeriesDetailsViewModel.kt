package com.dwh.movieapp.ui.series.popular.series

import android.app.Application
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.dwh.domain.models.entities.MovieEntinty
import com.dwh.domain.models.entities.SerieEntity
import com.dwh.domain.models.get.movies.details.MoviesDetails
import com.dwh.domain.models.get.series.details.SeriesDetails
import com.dwh.movieapp.repository.LocalSerieRepository
import com.dwh.movieapp.repository.MoviesRepository
import com.dwh.movieapp.repository.SeriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mx.com.satoritech.web.NetworkResult
import javax.inject.Inject

@HiltViewModel
class PopularSeriesDetailsViewModel@Inject constructor(
    private val seriesRepository: SeriesRepository,
    private val localSerieRepository: LocalSerieRepository,
    application: Application
): AndroidViewModel(application) {

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _serieDetails = mutableStateOf(SeriesDetails())
    val serieDetails: MutableState<SeriesDetails> = _serieDetails

    fun getSerieDetails(tvId: Long) = viewModelScope.launch {
        seriesRepository.getSerieDetails(tvId).collect {
            when(it){
                is NetworkResult.Success -> {
                    _isLoading.value = false
                    it.data?.let {  details ->
                        _serieDetails.value = details
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

    private val _serieDetailsLocal = mutableStateOf(SerieEntity())
    val serieDetailsLocal: MutableState<SerieEntity> = _serieDetailsLocal

    fun getSerieDetailsDataBase(serieId: Int) = viewModelScope.launch {
        _serieDetailsLocal.value = localSerieRepository.findSerieById(serieId)
    }
}