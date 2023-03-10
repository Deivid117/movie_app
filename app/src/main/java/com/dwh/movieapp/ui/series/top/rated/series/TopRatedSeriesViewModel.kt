package com.dwh.movieapp.ui.series.top.rated.series

import android.app.Application
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dwh.domain.models.entities.TopRatedMoviesEntity
import com.dwh.domain.models.entities.TopRatedSeriesEntity
import com.dwh.domain.models.entities.toTopRatedSeriesDB
import com.dwh.domain.models.get.series.popular.upcoming.Series
import com.dwh.movieapp.repository.LocalSerieRepository
import com.dwh.movieapp.repository.SeriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mx.com.satoritech.web.NetworkResult
import javax.inject.Inject

@HiltViewModel
class TopRatedSeriesViewModel@Inject constructor(
    private val seriesRepository: SeriesRepository,
    private val localSerieRepository: LocalSerieRepository,
    application: Application
): AndroidViewModel(application) {

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _topRatedSeries = mutableStateOf(Series())
    val topRatedSeries: MutableState<Series> = _topRatedSeries

    fun getTopRatedSeries() = viewModelScope.launch {
        seriesRepository.getTopRatedSeries().collect {
            when(it){
                is NetworkResult.Success -> {
                    _isLoading.value = false
                    it.data?.let {  series ->
                        _topRatedSeries.value = series
                        localSerieRepository.insertTopRatedSeriesEntity(series.results.map { seriesResult -> seriesResult.toTopRatedSeriesDB() })
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

    private val _topRatedSeriesListLocal = MutableLiveData<List<TopRatedSeriesEntity>>(listOf())
    val topRatedSeriesListLocal: LiveData<List<TopRatedSeriesEntity>> = _topRatedSeriesListLocal

    fun getTopRatedMoviesDataBase() = viewModelScope.launch {
        _topRatedSeriesListLocal.postValue(localSerieRepository.getAllTopRatedSeriesEntity())
    }
}