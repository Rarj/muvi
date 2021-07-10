package rio.arj.nsbtechnicaltest.helper

import java.text.SimpleDateFormat
import java.util.*

class DateHelper {
  fun formatDate(pattern: String, date: String?): String {
    if (date.isNullOrBlank()) {
      return "Unknown Release Date"
    }

    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.UK)
    val convertedDate = formatter.parse(date)

    return SimpleDateFormat(pattern, Locale("in", "ID")).format(convertedDate ?: Date())
  }
}