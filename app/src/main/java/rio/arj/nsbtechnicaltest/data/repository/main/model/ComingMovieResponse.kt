package rio.arj.nsbtechnicaltest.data.repository.main.model

import com.google.gson.annotations.SerializedName

data class ComingMovieResponse(
  val id: String,
  @SerializedName("poster_path")
  val posterPath: String
)