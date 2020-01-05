package dev.grack.features.pastmatch

import androidx.lifecycle.MutableLiveData
import dev.grack.base.BaseViewModel
import dev.grack.network.ApiConfiguration
import dev.grack.repository.leaguelist.LeagueRepository
import dev.grack.repository.leaguelist.model.League
import javax.inject.Inject

class PastMatchViewModel @Inject constructor(private val service: ApiConfiguration) : BaseViewModel() {

  var listLeagues = MutableLiveData<List<League>>()

  fun loadSoccerLeague() {
    val repository = LeagueRepository(service)
    addToDisposable(repository.loadLeagueList()
          .subscribe {
            listLeagues.value = it
          })
  }

}