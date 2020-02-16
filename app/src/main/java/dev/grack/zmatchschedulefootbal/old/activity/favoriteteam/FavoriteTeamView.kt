package dev.grack.zmatchschedulefootbal.old.activity.favoriteteam

import dev.grack.zmatchschedulefootbal.db.FavoriteTeam

interface FavoriteTeamView {
    fun showFavoriteTeamList(data: List<FavoriteTeam>)
}