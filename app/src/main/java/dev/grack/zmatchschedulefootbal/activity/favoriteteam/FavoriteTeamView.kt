package dev.grack.zmatchschedulefootbal.activity.favoriteteam

import dev.grack.zmatchschedulefootbal.db.FavoriteTeam

interface FavoriteTeamView {
    fun showFavoriteTeamList(data: List<FavoriteTeam>)
}