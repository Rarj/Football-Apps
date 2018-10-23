package dev.grack.matchschedulefootbal.activity.favorite

import android.content.Context
import dev.grack.matchschedulefootbal.db.Favorite
import dev.grack.matchschedulefootbal.db.database
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavoritePresenter(private val view: FavoriteView,
                        private val context: Context) {

    fun getFavorite() {
        context.database.use {
            val result = select(Favorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Favorite>())
            view.showFavoriteList(favorite)
        }
    }
}