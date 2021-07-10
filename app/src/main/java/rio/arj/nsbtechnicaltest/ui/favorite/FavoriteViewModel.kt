package rio.arj.nsbtechnicaltest.ui.favorite

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import rio.arj.nsbtechnicaltest.data.repository.favorite.FavoriteRepositoryImpl

class FavoriteViewModel(private val favoriteRepository: FavoriteRepositoryImpl) : ViewModel() {

  fun findAll() = flow {
    emit(favoriteRepository.findAll())
  }.flowOn(Dispatchers.IO)

  fun deleteMovie(movieId: String) = flow {
    emit(favoriteRepository.deleteMovie(movieId))
  }.flowOn(Dispatchers.IO)

  fun findMovies(query: String) = flow {
    emit(favoriteRepository.searchFavorite(query))
  }.flowOn(Dispatchers.IO)
}