package rio.arj.nsbtechnicaltest.data.repository.popular.model

import com.google.gson.annotations.SerializedName

data class CreditResponse(
  val cast: List<Credit>
)

data class Credit(
  val id: String,
  val name: String,
  @SerializedName("profile_path")
  val profilePath: String
) {
  override fun toString(): String {
    return name
  }
}
