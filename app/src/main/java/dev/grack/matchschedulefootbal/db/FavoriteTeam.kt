package dev.grack.matchschedulefootbal.db

data class FavoriteTeam(val id: Long?,
                        val idTeam: String?,
                        val strTeam: String?,
                        val strTeamBadge: String?,
                        val strFormedYear: String?,
                        val strStadium: String?,
                        val strDescriptionEN: String?) {

    companion object {
        const val TABLE_FAVORITE_TEAM: String = "TABLE_FAVORITE_TEAM"
        const val ID: String = "ID_"
        const val ID_TEAM: String = "ID_TEAM"
        const val STR_TEAM: String = "STR_TEAM"
        const val STR_TEAM_BADGE: String = "STR_TEAM_BADGE"
        const val STR_FORMED_YEAR: String = "STR_FORMED_YEAR"
        const val STR_STADIUM: String = "STR_STADIUM"
        const val STR_DESCRIPTION_EN: String = "STR_DESCRIPTION_EN"
    }
}