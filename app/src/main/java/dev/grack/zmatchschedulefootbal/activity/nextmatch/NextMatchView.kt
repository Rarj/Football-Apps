package dev.grack.zmatchschedulefootbal.activity.nextmatch

import dev.grack.zmatchschedulefootbal.model.Event
import dev.grack.zmatchschedulefootbal.model.League

interface NextMatchView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Event>)
    fun showLeagueList(data: List<League>)
}