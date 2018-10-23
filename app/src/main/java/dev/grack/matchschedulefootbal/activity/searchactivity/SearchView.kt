package dev.grack.matchschedulefootbal.activity.searchactivity

import dev.grack.matchschedulefootbal.model.EventSearch

interface SearchView {
    fun showLoading()
    fun hideLoading()
    fun showSearchList(data: List<EventSearch>)
}