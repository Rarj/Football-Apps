package dev.grack.repository.nextmatch.model

import com.google.gson.annotations.SerializedName
import dev.grack.Utils

class Event(
      @SerializedName("dateEvent")
      var dateEvent: String? = "",
      @SerializedName("idAwayTeam")
      var idAwayTeam: String? = "",
      @SerializedName("idEvent")
      var idEvent: String? = "",
      @SerializedName("idHomeTeam")
      var idHomeTeam: String? = "",
      @SerializedName("idLeague")
      var idLeague: String? = "",
      @SerializedName("intAwayScore")
      var intAwayScore: String? = "",
      @SerializedName("intHomeScore")
      var intHomeScore: String? = "",
      @SerializedName("strAwayTeam")
      var strAwayTeam: String? = "",
      @SerializedName("strDescriptionEN")
      var strDescriptionEN: String? = "",
      @SerializedName("strHomeTeam")
      var strHomeTeam: String? = "",
      @SerializedName("strLeague")
      var strLeague: String? = "",
      @SerializedName("strTime")
      var strTime: String? = ""
) {

  fun convertDate(): String? {
    return Utils.formatDate(dateEvent.toString())
  }

  fun convertTime(): String? {
    return Utils.formatTime(strTime.toString())
  }

}