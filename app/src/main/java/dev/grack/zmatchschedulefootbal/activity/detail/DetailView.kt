package dev.grack.zmatchschedulefootbal.activity.detail

import dev.grack.zmatchschedulefootbal.model.Event
import dev.grack.zmatchschedulefootbal.model.Team

interface DetailView {
    fun showLoading()
    fun hideLoading()
    fun showImageAwayTeam(data: List<Team>)
    fun showImageHomeTeam(data: List<Team>)
    fun showDetailTeam(data: List<Event>)
}