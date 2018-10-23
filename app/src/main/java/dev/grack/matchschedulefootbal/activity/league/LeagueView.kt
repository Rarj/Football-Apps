package dev.grack.matchschedulefootbal.activity.league

import dev.grack.matchschedulefootbal.model.League
import dev.grack.matchschedulefootbal.model.TeamSearch

interface LeagueView {
    fun showLeague(data: List<League>)
    fun showLoading()
    fun hideLoading()
    fun showLeagueList(data: List<TeamSearch>)
}