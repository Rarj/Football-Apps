package dev.grack.repository.leaguelist.model


import com.google.gson.annotations.SerializedName

data class League(
      @SerializedName("idLeague")
      var idLeague: String?,
      @SerializedName("strLeague")
      var strLeague: String?,
      @SerializedName("strLeagueAlternate")
      var strLeagueAlternate: String?,
      @SerializedName("strSport")
      var strSport: String?
) {
  override fun toString(): String {
    return strLeague.toString()
  }
}