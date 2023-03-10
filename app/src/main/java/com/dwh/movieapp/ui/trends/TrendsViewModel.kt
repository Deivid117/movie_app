package com.dwh.movieapp.ui.trends

import android.app.Application
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dwh.domain.models.entities.MovieEntinty
import com.dwh.domain.models.entities.TrendEntity
import com.dwh.domain.models.entities.toDatabase
import com.dwh.domain.models.get.trends.Trends
import com.dwh.movieapp.repository.LocalTrendRepository
import com.dwh.movieapp.repository.TrendsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mx.com.satoritech.web.NetworkResult
import javax.inject.Inject

@HiltViewModel
class TrendsViewModel@Inject constructor(
    private val trendsRepository: TrendsRepository,
    private val localTrendRepository: LocalTrendRepository,
    application: Application
): AndroidViewModel(application) {

    private val _trends = mutableStateOf(Trends())
    val trends: MutableState<Trends> = _trends

    fun getTrends(mediaType: String, timeWindonw: String) = viewModelScope.launch {
        trendsRepository.getTrends(mediaType, timeWindonw).collect {
            when(it) {
                is NetworkResult.Success -> {
                    it.data?.let {  trends ->
                        _trends.value = trends
                        localTrendRepository.insert(trends.results.map { trendsResults -> trendsResults.toDatabase() })
                    }
                }
                is NetworkResult.Loading -> {

                }
                is NetworkResult.Error -> {
                    Toast.makeText(getApplication(), it.message?:"", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private val _trendListLocal = MutableLiveData<List<TrendEntity>>(listOf())
    val trendListLocal: LiveData<List<TrendEntity>> = _trendListLocal

    fun getTrendsDataBase() = viewModelScope.launch {
        _trendListLocal.postValue(localTrendRepository.getAllTrends())
    }
}