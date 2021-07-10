package rio.arj.nsbtechnicaltest.data.repository.detail.model


data class Genre(
  val id: Int?,
  val name: String?
) {
  override fun toString(): String {
    return name.toString()
  }
}