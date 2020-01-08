package dev.grack.features.pastmatch

import androidx.lifecycle.MutableLiveData
import dev.grack.Constant.ID_ENGLISH_PREMIER_LEAGUE
import dev.grack.base.BaseViewModel
import dev.grack.network.ApiConfiguration
import dev.grack.repository.leaguelist.LeagueRepository
import dev.grack.repository.leaguelist.model.League
import dev.grack.repository.pastmatch.PastMatchRepository
import dev.grack.repository.pastmatch.model.PastMatchResponse
import javax.inject.Inject

class PastMatchViewModel @Inject constructor(service: ApiConfiguration) : BaseViewModel() {

  private val pastMatchRepository = PastMatchRepository(service)
  private val leagueRepository = LeagueRepository(service)

  var listLeagues = MutableLiveData<List<League>>()
  var selectedLeague = MutableLiveData<String>()
  var selectedIdLeague = MutableLiveData<String>()

  var listPastMatch = MutableLiveData<PastMatchResponse>()

  fun loadSoccerLeague() {
    addToDisposable(leagueRepository.loadLeagueList()
          .subscribe({ leagueResponse ->
            listLeagues.value = leagueResponse
          }, {

          }))
  }

  fun loadPastMatch(id: String? = ID_ENGLISH_PREMIER_LEAGUE) {
    addToDisposable(pastMatchRepository.loadPastMatch(id)
          .subscribe({ pastMatchResponse ->
            listPastMatch.value = pastMatchResponse
          }, {

          }))
  }

}