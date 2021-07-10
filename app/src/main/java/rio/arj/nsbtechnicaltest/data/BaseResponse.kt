package rio.arj.nsbtechnicaltest.data

data class BaseResponse<T>(
  val results: List<T>
)