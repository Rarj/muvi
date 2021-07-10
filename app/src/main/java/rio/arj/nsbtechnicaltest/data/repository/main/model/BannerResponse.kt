package rio.arj.nsbtechnicaltest.data.repository.main.model

import com.google.gson.annotations.SerializedName

data class BannerResponse(
  val id: String,
  @SerializedName("backdrop_path")
  val backdropUrl: String
)