package dev.grack.matchschedulefootbal.activity.favorite

import dev.grack.matchschedulefootbal.db.Favorite

interface FavoriteView {
    fun showFavoriteList(data: List<Favorite>)
}