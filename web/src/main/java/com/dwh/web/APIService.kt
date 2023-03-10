package mx.com.satoritech.web

import com.dwh.domain.models.get.movies.popular.upcoming.Movies
import com.dwh.domain.models.get.series.popular.upcoming.Series
import com.dwh.domain.models.get.movies.details.MoviesDetails
import com.dwh.domain.models.get.series.details.SeriesDetails
import com.dwh.domain.models.get.trends.Trends
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {

    //MOVIES
    @GET(APIConstants.SERVER_URL + "movie/popular")
    suspend fun getPopularMovies(): Response<Movies>

    @GET(APIConstants.SERVER_URL + "movie/upcoming")
    suspend fun getUpcomingMovies(): Response<Movies>

    @GET(APIConstants.SERVER_URL + "movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movieId: Long): Response<MoviesDetails>

    @GET(APIConstants.SERVER_URL + "movie/top_rated")
    suspend fun getTopRatedMovies(): Response<Movies>

    //SERIES
    @GET(APIConstants.SERVER_URL + "tv/popular")
    suspend fun getPopularSeries(): Response<Series>

    @GET(APIConstants.SERVER_URL + "tv/{tv_id}")
    suspend fun getSerieDetails(@Path("tv_id") tvId: Long): Response<SeriesDetails>

    @GET(APIConstants.SERVER_URL + "tv/top_rated")
    suspend fun getTopRatedSeries(): Response<Series>

    //TRENDING
    @GET(APIConstants.SERVER_URL + "trending/{media_type}/{time_window}")
    suspend fun getTrending(
        @Path("media_type") mediaType: String,
        @Path("time_window") timeWindow: String
    ): Response<Trends>
}