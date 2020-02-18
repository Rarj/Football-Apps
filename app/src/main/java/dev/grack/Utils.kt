package dev.grack

import java.text.SimpleDateFormat
import java.util.*

object Utils {

  fun formatDate(date: String): String {
    return if (date.isNotBlank() && date != "null") {
      val pattern = SimpleDateFormat("yyyy-mm-dd", Locale.UK)
      val convertedDate = pattern.parse(date)

      SimpleDateFormat("E, dd MMMM yyyy", Locale.UK).format(convertedDate!!)
    } else {
      ""
    }
  }

  fun formatTime(time: String): String {
    return if (time.isNotBlank() && time != "null") {
      val pattern = SimpleDateFormat("HH:mm", Locale.UK)
      val formatter = SimpleDateFormat("HH:mm", Locale.UK)
      formatter.format(pattern.parse(time)!!)

    } else {
      ""
    }
  }

}