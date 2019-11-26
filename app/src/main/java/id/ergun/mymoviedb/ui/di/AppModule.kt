package id.ergun.mymoviedb.ui.di

import id.ergun.mymoviedb.data.local.db.MovieData
import id.ergun.mymoviedb.data.repository.MovieRepository
import id.ergun.mymoviedb.data.repository.MovieRepositoryImpl
import id.ergun.mymoviedb.ui.module.movie.MovieViewModel
import id.ergun.mymoviedb.ui.module.movie.detail.MovieDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by erikgunawan on 24/11/19.
 */
val appModule = module {

    single { MovieData() }

    factory<MovieRepository> { MovieRepositoryImpl(get()) }

    viewModel { MovieViewModel(get()) }
    viewModel { MovieDetailViewModel(get()) }


}