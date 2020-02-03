package dev.grack.features.pastmatch

import androidx.lifecycle.MutableLiveData
import dev.grack.Constant.ID_ENGLISH_PREMIER_LEAGUE
import dev.grack.base.BaseViewModel
import dev.grack.network.ApiConfiguration
import dev.grack.repository.leaguelist.LeagueRepository
import dev.grack.repository.leaguelist.model.League
import dev.grack.repository.pastmatch.MatchModel
import dev.grack.repository.pastmatch.PastMatchRepository
import dev.grack.repository.pastmatch.model.Event
import dev.grack.repository.pastmatch.model.PastMatchResponse
import dev.grack.repository.team.TeamRepository
import dev.grack.repository.team.model.TeamResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PastMatchViewModel @Inject constructor(service: ApiConfiguration) : BaseViewModel() {

  private val pastMatchRepository = PastMatchRepository(service)
  private val leagueRepository = LeagueRepository(service)
  private val teamRepository = TeamRepository(service)

  var listLeagues = MutableLiveData<List<League>>()
  var selectedLeague = MutableLiveData<String>()
  var selectedIdLeague = MutableLiveData<String>()

  var listPastMatch = MutableLiveData<PastMatchResponse>()

  var teamModel = MutableLiveData<TeamResponse>()

  var matchModel = mutableListOf<MatchModel>()

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
            matchModel.clear()

            pastMatchResponse.events?.forEach { event ->
              mappingTeam(event, pastMatchResponse)
            }

          }, {

          }))
  }

  private fun mappingTeam(event: Event, pastMatchResponse: PastMatchResponse) {
    var logoHome: String
    var logoAway: String

    addToDisposable(Observable.zip(
          loadTeamBagde(event.idHomeTeam.toString()),
          loadTeamBagde(event.idAwayTeam.toString()),
          BiFunction<TeamResponse, TeamResponse, Boolean> { responseHome, responseAway ->
            logoHome = responseHome.teams?.get(0)?.strTeamBadge.toString()
            logoAway = responseAway.teams?.get(0)?.strTeamBadge.toString()

            matchModel.add(MatchModel(
                  event.idHomeTeam,
                  event.idAwayTeam,
                  event.strHomeTeam,
                  event.strAwayTeam,
                  logoHome,
                  logoAway,
                  event.intHomeScore.toString(),
                  event.intAwayScore.toString(),
                  event.convertDate(),
                  event.convertTime()
            ))

            true
          }).subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe {
            listPastMatch.value = pastMatchResponse
          })
  }

  fun loadTeamBagde(id: String): Observable<TeamResponse> {
    return teamRepository.loadTeamDetail(id)
          .map { teamResponse ->
            teamModel.value = teamResponse
            teamResponse
          }
  }

  override fun onCleared() {
    super.onCleared()
    clearDisposable()
  }

}