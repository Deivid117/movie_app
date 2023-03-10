package mx.com.satoritech.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dwh.database.dao.*
import com.dwh.domain.models.entities.*

@Database(
    entities = [
        MovieEntinty::class,
        SerieEntity::class,
        TrendEntity::class,
        UpcomingMoviesEntity::class,
        TopRatedSeriesEntity::class,
        TopRatedMoviesEntity::class
               ],
    version = 1,
    exportSchema = false
)
abstract class DBMovieApp : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun serieDao(): SerieDao
    abstract fun trendDao(): TrendDao
    abstract fun upcomingMovieDao(): UpcomingMoviesDao
    abstract fun topRatedMovies(): TopRatedMovieDao
    abstract fun topRatedSeries(): TopRatedSerieDao

    companion object {
        @JvmStatic
        fun newInstance(context: Context): DBMovieApp {
            return Room
                .databaseBuilder(context, DBMovieApp::class.java, "DBMovieApp")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}