package dev.grack.zmatchschedulefootbal.old.activity.searchactivity

import dev.grack.zmatchschedulefootbal.model.EventSearch

interface SearchView {
    fun showLoading()
    fun hideLoading()
    fun showSearchList(data: List<EventSearch>)
}