package rio.arj.nsbtechnicaltest.di

import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.FlowPreview
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rio.arj.nsbtechnicaltest.data.repository.detail.DetailRepositoryImpl
import rio.arj.nsbtechnicaltest.data.repository.favorite.FavoriteDao
import rio.arj.nsbtechnicaltest.data.repository.favorite.FavoriteRepositoryImpl
import rio.arj.nsbtechnicaltest.data.repository.main.MainRepositoryImpl
import rio.arj.nsbtechnicaltest.data.repository.popular.PopularDataSource
import rio.arj.nsbtechnicaltest.data.repository.popular.PopularRepositoryImpl
import rio.arj.nsbtechnicaltest.database.FavoriteDatabase
import rio.arj.nsbtechnicaltest.network.NetworkBuilder
import rio.arj.nsbtechnicaltest.ui.detail.DetailViewModel
import rio.arj.nsbtechnicaltest.ui.favorite.FavoriteViewModel
import rio.arj.nsbtechnicaltest.ui.main.MainViewModel
import rio.arj.nsbtechnicaltest.ui.popular.PopularViewModel

val appModules = module {
  single { NetworkBuilder().provideRetrofit() }
  single { NetworkBuilder().provideService(get()) }
}

@FlowPreview
val viewModelModules = module {
  viewModel { MainViewModel(get()) }
  viewModel { PopularViewModel(get()) }
  viewModel { DetailViewModel(get(), get()) }
  viewModel { FavoriteViewModel(get()) }
}

val repositoryModules = module {
  single { MainRepositoryImpl(get()) }
  single { PopularRepositoryImpl(get()) }
  single { PopularDataSource(get()) }
  single { DetailRepositoryImpl(get()) }
  single { FavoriteRepositoryImpl(get()) }
}

val databaseModules = module {
  fun provideDatabase(context: Context): FavoriteDatabase {
    return Room.databaseBuilder(context, FavoriteDatabase::class.java, "favorite.db")
      .fallbackToDestructiveMigration()
      .build()
  }

  fun provideFavoriteDao(database: FavoriteDatabase): FavoriteDao {
    return database.favoriteDao()
  }

  single { provideDatabase(androidContext()) }
  single { provideFavoriteDao(get()) }
}
