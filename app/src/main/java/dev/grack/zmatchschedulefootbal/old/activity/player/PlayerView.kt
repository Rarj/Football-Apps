package dev.grack.zmatchschedulefootbal.old.activity.player

import dev.grack.zmatchschedulefootbal.model.Player

interface PlayerView {
    fun showLoading()
    fun hideLoading()
    fun showSearchList(data: List<Player>)
}