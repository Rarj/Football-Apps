package dev.grack.zmatchschedulefootbal.activity.favorite

import dev.grack.zmatchschedulefootbal.db.Favorite

interface FavoriteView {
    fun showFavoriteList(data: List<Favorite>)
}