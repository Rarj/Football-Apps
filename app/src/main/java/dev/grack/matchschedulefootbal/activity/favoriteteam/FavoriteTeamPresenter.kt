package dev.grack.matchschedulefootbal.activity.favoriteteam

import android.content.Context
import dev.grack.matchschedulefootbal.db.FavoriteTeam
import dev.grack.matchschedulefootbal.db.database
import dev.grack.matchschedulefootbal.db.databaseTeam
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