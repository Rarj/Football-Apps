package dev.grack.matchschedulefootbal.db


data class Favorite(val id: Long?,
                    val idEvent: String?,
                    val idHomeTeam: String?,
                    val idAwayTeam: String?,
                    val strHomeTeam: String?,
                    val strAwayTeam: String?,
                    val strTime: String?,
                    val intHomeScore: String?,
                    val intAwayScore: String?,
                    val dateEvent: String?) {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val ID_EVENT: String = "ID_EVENT"
        const val TEAM_ID_HOME: String = "TEAM_ID_HOME"
        const val TEAM_ID_AWAY: String = "TEAM_ID_AWAY"
        const val TEAM_NAME_HOME: String = "TEAM_NAME_HOME"
        const val TEAM_NAME_AWAY: String = "TEAM_NAME_AWAY"
        const val TIME_MATCH: String = "TEAM_MATCH"
        const val TEAM_SCORE_HOME: String = "TEAM_SCORE_HOME"
        const val TEAM_SCORE_AWAY: String = "TEAM_SCORE_AWAY"
        const val TEAM_DATE_EVENT: String = "TEAM_DATE_EVENT"
    }
}