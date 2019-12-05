package id.ergun.mymoviedb.ui.di

import androidx.room.Room
import id.ergun.mymoviedb.data.Const
import id.ergun.mymoviedb.data.local.db.AppDatabase
import id.ergun.mymoviedb.data.remote.AppService
import id.ergun.mymoviedb.data.remote.AppServiceFactory
import id.ergun.mymoviedb.data.repository.movie.MovieRepository
import id.ergun.mymoviedb.data.repository.movie.MovieRepositoryImpl
import id.ergun.mymoviedb.data.repository.tvShow.TvShowRepository
import id.ergun.mymoviedb.data.repository.tvShow.TvShowRepositoryImpl
import id.ergun.mymoviedb.ui.module.movie.MovieViewModel
import id.ergun.mymoviedb.ui.module.movie.detail.MovieDetailViewModel
import id.ergun.mymoviedb.ui.module.tv.TvShowViewModel
import id.ergun.mymoviedb.ui.module.tv.detail.TvShowDetailViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by erikgunawan on 24/11/19.
 */
val appModule = module {

    single {
        AppServiceFactory(androidContext()).createService(
            AppService::class.java,
            true
        )
    }

    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            Const.DB_NAME
        ).build()
    }

    single { get<AppDatabase>().movieDao() }
    single { get<AppDatabase>().tvDao() }

    factory<MovieRepository> {
        MovieRepositoryImpl(
            androidContext(),
            get(),
            get()
        )
    }

    factory<TvShowRepository> {
        TvShowRepositoryImpl(
            androidContext(),
            get(),
            get()
        )
    }

    viewModel { TvShowViewModel(get()) }
    viewModel { TvShowDetailViewModel(get()) }

    viewModel { MovieViewModel(get()) }
    viewModel { MovieDetailViewModel(get()) }

}