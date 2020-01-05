package dev.grack.features.parent

import dev.grack.base.BaseViewModel
import dev.grack.network.ApiConfiguration
import dev.grack.repository.leaguelist.LeagueRepository
import dev.grack.repository.leaguelist.model.League
import io.reactivex.Observable
import javax.inject.Inject

class ParentViewModel @Inject constructor(val service: ApiConfiguration) : BaseViewModel() {

  fun loadSoccerLeague(): Observable<List<League>> {
    val reporitory = LeagueRepository(service)
    return reporitory.loadLeagueList()
          .map {
            it
          }
  }

}