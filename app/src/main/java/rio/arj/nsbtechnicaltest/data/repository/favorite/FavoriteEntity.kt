package rio.arj.nsbtechnicaltest.data.repository.favorite

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class FavoriteEntity(
  @PrimaryKey
  val movieId: String,
  val name: String,
  val releaseDate: String,
  val genre: String,
  val imageUrl: String
) {
  fun getGenreFormatted() = genre.replace(" ● ", ", ")
}