package rio.arj.nsbtechnicaltest.data.repository.favorite

import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
  suspend fun insert(movie: FavoriteEntity)
  suspend fun getMovie(id: String): List<FavoriteEntity>
  suspend fun deleteMovie(id: String)
  suspend fun findAll(): List<FavoriteEntity>
  suspend fun searchFavorite(keyword: String?): Flow<List<FavoriteEntity>>
}