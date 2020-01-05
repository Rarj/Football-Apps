package dev.grack.zmatchschedulefootbal.activity.detailteam

import dev.grack.zmatchschedulefootbal.model.TeamSearch

interface DetailTeamView {
    fun showDetailTeams(data: List<TeamSearch>)
}