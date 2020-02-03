package dev.grack.repository.pastmatch

data class MatchModel(
      val idHome: String?,
      val idAway: String?,
      val nameTeamHome: String?,
      val nameTeamAway: String?,
      val logoTeamHome: String?,
      val logoTeamAway: String?,
      val scoreHome: String?,
      val scoreAway: String?,
      val dateEvent: String?,
      val timeEvent: String?
)