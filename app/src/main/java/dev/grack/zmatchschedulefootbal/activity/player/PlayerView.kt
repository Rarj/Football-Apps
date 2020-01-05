package dev.grack.zmatchschedulefootbal.activity.player

import dev.grack.zmatchschedulefootbal.model.Player

interface PlayerView {
    fun showLoading()
    fun hideLoading()
    fun showSearchList(data: List<Player>)
}