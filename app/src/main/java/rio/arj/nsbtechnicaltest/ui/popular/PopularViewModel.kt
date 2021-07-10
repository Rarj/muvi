package rio.arj.nsbtechnicaltest.ui.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.flow
import rio.arj.nsbtechnicaltest.data.repository.popular.PopularDataSource
import rio.arj.nsbtechnicaltest.data.repository.popular.PopularRepositoryImpl

class PopularViewModel(private val repository: PopularRepositoryImpl) : ViewModel() {

  val genresMovie = flow {
    emit(repository.getGenres())
  }

  val popularMovies = Pager(
    config = PagingConfig(pageSize = 20),
    pagingSourceFactory = { PopularDataSource(repository) }
  ).flow.cachedIn(viewModelScope)

}