package dev.grack.matchschedulefootbal.activity.nextmatch

import dev.grack.matchschedulefootbal.model.Event
import dev.grack.matchschedulefootbal.model.League

interface NextMatchView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Event>)
    fun showLeagueList(data: List<League>)
}