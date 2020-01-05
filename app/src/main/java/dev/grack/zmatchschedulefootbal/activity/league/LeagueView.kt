package dev.grack.zmatchschedulefootbal.activity.league

import dev.grack.zmatchschedulefootbal.model.League
import dev.grack.zmatchschedulefootbal.model.TeamSearch

interface LeagueView {
    fun showLeague(data: List<League>)
    fun showLoading()
    fun hideLoading()
    fun showLeagueList(data: List<TeamSearch>)
}