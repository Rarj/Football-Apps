package dev.grack.repository.nextmatch.model

import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

class Event(
      @SerializedName("dateEvent")
      var dateEvent: String?,
      @SerializedName("dateEventLocal")
      var dateEventLocal: String?,
      @SerializedName("idAPIfootball")
      var idAPIfootball: Any?,
      @SerializedName("idAwayTeam")
      var idAwayTeam: String?,
      @SerializedName("idEvent")
      var idEvent: String?,
      @SerializedName("idHomeTeam")
      var idHomeTeam: String?,
      @SerializedName("idLeague")
      var idLeague: String?,
      @SerializedName("idSoccerXML")
      var idSoccerXML: String?,
      @SerializedName("intAwayScore")
      var intAwayScore: String?,
      @SerializedName("intAwayShots")
      var intAwayShots: Any?,
      @SerializedName("intHomeScore")
      var intHomeScore: String?,
      @SerializedName("intHomeShots")
      var intHomeShots: Any?,
      @SerializedName("intRound")
      var intRound: String?,
      @SerializedName("intSpectators")
      var intSpectators: Any?,
      @SerializedName("strAwayFormation")
      var strAwayFormation: Any?,
      @SerializedName("strAwayGoalDetails")
      var strAwayGoalDetails: String?,
      @SerializedName("strAwayLineupDefense")
      var strAwayLineupDefense: String?,
      @SerializedName("strAwayLineupForward")
      var strAwayLineupForward: String?,
      @SerializedName("strAwayLineupGoalkeeper")
      var strAwayLineupGoalkeeper: String?,
      @SerializedName("strAwayLineupMidfield")
      var strAwayLineupMidfield: String?,
      @SerializedName("strAwayLineupSubstitutes")
      var strAwayLineupSubstitutes: String?,
      @SerializedName("strAwayRedCards")
      var strAwayRedCards: String?,
      @SerializedName("strAwayTeam")
      var strAwayTeam: String?,
      @SerializedName("strAwayYellowCards")
      var strAwayYellowCards: String?,
      @SerializedName("strBanner")
      var strBanner: Any?,
      @SerializedName("strCircuit")
      var strCircuit: Any?,
      @SerializedName("strCity")
      var strCity: Any?,
      @SerializedName("strCountry")
      var strCountry: Any?,
      @SerializedName("strDate")
      var strDate: String?,
      @SerializedName("strDescriptionEN")
      var strDescriptionEN: String?,
      @SerializedName("strEvent")
      var strEvent: String?,
      @SerializedName("strEventAlternate")
      var strEventAlternate: String?,
      @SerializedName("strFanart")
      var strFanart: Any?,
      @SerializedName("strFilename")
      var strFilename: String?,
      @SerializedName("strHomeFormation")
      var strHomeFormation: Any?,
      @SerializedName("strHomeGoalDetails")
      var strHomeGoalDetails: String?,
      @SerializedName("strHomeLineupDefense")
      var strHomeLineupDefense: String?,
      @SerializedName("strHomeLineupForward")
      var strHomeLineupForward: String?,
      @SerializedName("strHomeLineupGoalkeeper")
      var strHomeLineupGoalkeeper: String?,
      @SerializedName("strHomeLineupMidfield")
      var strHomeLineupMidfield: String?,
      @SerializedName("strHomeLineupSubstitutes")
      var strHomeLineupSubstitutes: String?,
      @SerializedName("strHomeRedCards")
      var strHomeRedCards: String?,
      @SerializedName("strHomeTeam")
      var strHomeTeam: String?,
      @SerializedName("strHomeYellowCards")
      var strHomeYellowCards: String?,
      @SerializedName("strLeague")
      var strLeague: String?,
      @SerializedName("strLocked")
      var strLocked: String?,
      @SerializedName("strMap")
      var strMap: Any?,
      @SerializedName("strPoster")
      var strPoster: Any?,
      @SerializedName("strResult")
      var strResult: String?,
      @SerializedName("strSeason")
      var strSeason: String?,
      @SerializedName("strSport")
      var strSport: String?,
      @SerializedName("strTVStation")
      var strTVStation: Any?,
      @SerializedName("strThumb")
      var strThumb: String?,
      @SerializedName("strTime")
      var strTime: String?,
      @SerializedName("strTimeLocal")
      var strTimeLocal: String?,
      @SerializedName("strTweet1")
      var strTweet1: String?,
      @SerializedName("strTweet2")
      var strTweet2: String?,
      @SerializedName("strTweet3")
      var strTweet3: String?,
      @SerializedName("strVideo")
      var strVideo: String?
) {

  fun convertDate(): String? {
    return if (dateEvent.toString().isNotBlank() && dateEvent.toString() != "null") {
      val pattern = SimpleDateFormat("yyyy-mm-dd", Locale.UK)
      val convertedDate = pattern.parse(dateEvent.toString())

      SimpleDateFormat("E, D MMMM YYYY", Locale.UK).format(convertedDate!!)
    } else {
      ""
    }
  }

  fun convertTime(): String? {
    return if (strTime.toString().isNotBlank() && strTime.toString() != "null") {
      val pattern = SimpleDateFormat("HH:mm", Locale.UK)
      val formatter = SimpleDateFormat("HH:mm", Locale.UK)
      formatter.format(pattern.parse(strTime.toString())!!)

    } else {
      ""
    }
  }

}