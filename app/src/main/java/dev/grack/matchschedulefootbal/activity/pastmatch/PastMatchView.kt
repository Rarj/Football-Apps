package dev.grack.matchschedulefootbal.activity.pastmatch

import dev.grack.matchschedulefootbal.model.Event
import dev.grack.matchschedulefootbal.model.League

interface PastMatchView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Event>)
    fun showLeagueList(data: List<League>)
}