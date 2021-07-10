package rio.arj.nsbtechnicaltest.data.repository.popular.model

import com.google.gson.annotations.SerializedName

data class PopularMoviePagingResponse(
  val id: String,
  @SerializedName("genre_ids")
  val genreIds: List<Int>,
  @SerializedName("original_title")
  val originalTitle: String,
  @SerializedName("poster_path")
  val posterPath: String,
  var genresName: MutableList<String>?
)
