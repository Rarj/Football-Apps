package dev.grack.zmatchschedulefootbal.activity.favoriteteam

import android.content.Context
import dev.grack.zmatchschedulefootbal.db.FavoriteTeam
import dev.grack.zmatchschedulefootbal.db.database
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavoriteTeamPresenter(private val view: FavoriteTeamView,
                            private val context: Context) {

    fun getFavoriteTeam() {
        context.database.use {
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            view.showFavoriteTeamList(favorite)
        }
    }
}