package dev.grack.matchschedulefootbal.activity.detail

import dev.grack.matchschedulefootbal.model.Event
import dev.grack.matchschedulefootbal.model.Team

interface DetailView {
    fun showLoading()
    fun hideLoading()
    fun showImageAwayTeam(data: List<Team>)
    fun showImageHomeTeam(data: List<Team>)
    fun showDetailTeam(data: List<Event>)
}