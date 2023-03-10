package com.dwh.movieapp.ui.series.popular.series

import android.app.Application
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dwh.domain.models.entities.SerieEntity
import com.dwh.domain.models.entities.TrendEntity
import com.dwh.domain.models.entities.toDatabase
import com.dwh.domain.models.get.series.popular.upcoming.Series
import com.dwh.movieapp.repository.LocalSerieRepository
import com.dwh.movieapp.repository.SeriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mx.com.satoritech.web.NetworkResult
import javax.inject.Inject

@HiltViewModel
class PopularSeriesViewModel@Inject constructor(
    private val seriesRepository: SeriesRepository,
    private val localSerieRepository: LocalSerieRepository,
    application: Application
): AndroidViewModel(application) {

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _series = mutableStateOf(Series())
    val series: MutableState<Series> = _series

    fun getPopularSeries() = viewModelScope.launch {
        seriesRepository.getPopularSeries().collect {
            when(it){
                is NetworkResult.Success -> {
                    _isLoading.value = false
                    it.data?.let {  series ->
                        _series.value = series
                        localSerieRepository.insert(series.results.map { serieResults -> serieResults.toDatabase() })
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

    private val _seriesListLocal = MutableLiveData<List<SerieEntity>>(listOf())
    val seriesListLocal: LiveData<List<SerieEntity>> = _seriesListLocal

    fun getSeriesDataBase() = viewModelScope.launch {
        _seriesListLocal.postValue(localSerieRepository.getAllSeries())
    }
}