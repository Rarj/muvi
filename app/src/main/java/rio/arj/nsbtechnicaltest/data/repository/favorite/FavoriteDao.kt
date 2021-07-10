package rio.arj.nsbtechnicaltest.data.repository.favorite

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insert(movie: FavoriteEntity)

  @Query("SELECT * FROM FavoriteEntity WHERE movieId=:id")
  suspend fun getMovie(id: String): List<FavoriteEntity>

  @Query("DELETE FROM FavoriteEntity WHERE movieId=:id")
  suspend fun deleteMovie(id: String)

  @Query("SELECT * FROM FavoriteEntity")
  suspend fun findAll(): List<FavoriteEntity>

  @Query("SELECT * FROM FavoriteEntity WHERE name LIKE '%' || :search || '%'")
  fun getSearchedDogs(search: String?): Flow<List<FavoriteEntity>>

}