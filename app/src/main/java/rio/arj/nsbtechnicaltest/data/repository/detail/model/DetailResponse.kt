package rio.arj.nsbtechnicaltest.data.repository.detail.model

import com.google.gson.annotations.SerializedName
import rio.arj.nsbtechnicaltest.helper.DateHelper

data class DetailResponse(
  val genres: List<Genre>?,
  @SerializedName("id")
  val id: Int?,
  val overview: String?,
  @SerializedName("poster_path")
  val posterPath: String?,
  @SerializedName("backdrop_path")
  val backdropPath: String?,
  val runtime: Int?,
  @SerializedName("title")
  val title: String?,
  @SerializedName("release_date")
  val releaseDate: String?
) {
  fun getGenreFormatted() = genres.toString()
    .replace("[", "")
    .replace("]", "")
    .replace(", ", " ● ")

  fun getReleaseDateFormatted() = DateHelper().formatDate("dd MMMM yyyy", releaseDate)
}