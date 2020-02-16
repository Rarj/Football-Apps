package dev.grack.zmatchschedulefootbal.old.activity.favorite

import dev.grack.zmatchschedulefootbal.db.Favorite

interface FavoriteView {
    fun showFavoriteList(data: List<Favorite>)
}