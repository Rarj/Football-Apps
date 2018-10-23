package dev.grack.matchschedulefootbal.activity.favoriteteam

import dev.grack.matchschedulefootbal.db.FavoriteTeam

interface FavoriteTeamView {
    fun showFavoriteTeamList(data: List<FavoriteTeam>)
}