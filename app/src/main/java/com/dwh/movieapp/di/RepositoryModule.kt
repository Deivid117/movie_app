package mx.com.satoritech.balanppy.di

import com.dwh.movieapp.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindsMoviesRepository(moviesRepositoryImp: MoviesRepositoryImp): MoviesRepository

    @Binds
    abstract fun bindsSeriesRepository(seriesRepositoryImp: SeriesRepositoryImp): SeriesRepository

    @Binds
    abstract fun bindsTrendsRepository(trendsRepositoryImp: TrendsRepositoryImp): TrendsRepository

    @Binds
    abstract fun bindsLocalMoviesRepository(localMovieRepositoryImp: LocalMovieRepositoryImp): LocalMovieRepository

    @Binds
    abstract fun bindsLocalSeriesRepository(localSerieRepositoryImp: LocalSerieRepositoryImp): LocalSerieRepository

    @Binds
    abstract fun bindsLocalTrendsRepository(localTrendRepositoryImp: LocalTrendRepositoryImp): LocalTrendRepository
}