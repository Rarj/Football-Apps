package dev.grack.matchschedulefootbal.activity.player

import dev.grack.matchschedulefootbal.model.Player

interface PlayerView {
    fun showLoading()
    fun hideLoading()
    fun showSearchList(data: List<Player>)
}