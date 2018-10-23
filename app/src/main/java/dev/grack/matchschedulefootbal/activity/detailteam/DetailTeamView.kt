package dev.grack.matchschedulefootbal.activity.detailteam

import dev.grack.matchschedulefootbal.model.TeamSearch

interface DetailTeamView {
    fun showDetailTeams(data: List<TeamSearch>)
}