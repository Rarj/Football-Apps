package dev.grack.zmatchschedulefootbal.old.activity.searchteams

import dev.grack.zmatchschedulefootbal.model.TeamSearch

interface SearchTeamView {
    fun showLoading()
    fun hideLoading()
    fun showSearchList(data: List<TeamSearch>)
}