package rio.arj.nsbtechnicaltest.ui.detail

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import rio.arj.nsbtechnicaltest.data.repository.detail.DetailRepositoryImpl
import rio.arj.nsbtechnicaltest.data.repository.favorite.FavoriteEntity
import rio.arj.nsbtechnicaltest.data.repository.favorite.FavoriteRepositoryImpl

@FlowPreview
class DetailViewModel(
  private val detailRepository: DetailRepositoryImpl,
  private val favoriteRepository: FavoriteRepositoryImpl
) : ViewModel() {

  fun getDetailMovie(id: Int) = flow {
    emit(detailRepository.getDetailMovie(id))
  }.flatMapMerge { it }

  fun creditsMovie(movieId: Int) = flow {
    emit(detailRepository.getCredits(movieId))
  }.flatMapMerge { it }.map { it }

  fun addFavorite(movie: FavoriteEntity) = flow {
    emit(favoriteRepository.insert(movie))
  }.flowOn(Dispatchers.IO)

  fun findMovie(movieId: String) = flow {
    emit(favoriteRepository.getMovie(movieId))
  }.flowOn(Dispatchers.IO)

  fun deleteMovie(movieId: String) = flow {
    emit(favoriteRepository.deleteMovie(movieId))
  }.flowOn(Dispatchers.IO)
}