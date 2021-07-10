package rio.arj.nsbtechnicaltest.data.repository.favorite

import kotlinx.coroutines.flow.Flow

class FavoriteRepositoryImpl(private val dao: FavoriteDao) : FavoriteRepository {
  override suspend fun insert(movie: FavoriteEntity) {
    dao.insert(movie)
  }

  override suspend fun getMovie(id: String): List<FavoriteEntity> {
    return dao.getMovie(id)
  }

  override suspend fun deleteMovie(id: String) {
    dao.deleteMovie(id)
  }

  override suspend fun findAll(): List<FavoriteEntity> {
    return dao.findAll()
  }

  override suspend fun searchFavorite(keyword: String?): Flow<List<FavoriteEntity>> {
    return dao.getSearchedDogs(keyword)
  }
}