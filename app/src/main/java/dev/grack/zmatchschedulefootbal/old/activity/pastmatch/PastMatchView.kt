package dev.grack.zmatchschedulefootbal.old.activity.pastmatch

import dev.grack.zmatchschedulefootbal.model.Event
import dev.grack.zmatchschedulefootbal.model.League

interface PastMatchView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Event>)
    fun showLeagueList(data: List<League>)
}