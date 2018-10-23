package dev.grack.matchschedulefootbal.activity.searchteams

import dev.grack.matchschedulefootbal.model.TeamSearch

interface SearchTeamView {
    fun showLoading()
    fun hideLoading()
    fun showSearchList(data: List<TeamSearch>)
}