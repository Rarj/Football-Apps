package dev.grack.features.nextmatch

import androidx.lifecycle.MutableLiveData
import dev.grack.Constant
import dev.grack.base.BaseViewModel
import dev.grack.network.ApiConfiguration
import dev.grack.repository.leaguelist.LeagueRepository
import dev.grack.repository.leaguelist.model.League
import dev.grack.repository.nextmatch.NextMatchRepository
import dev.grack.repository.nextmatch.model.Event
import dev.grack.repository.nextmatch.model.NextMatchResponse
import dev.grack.repository.pastmatch.MatchModel
import dev.grack.repository.team.TeamRepository
import dev.grack.repository.team.model.TeamResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NextMatchViewModel @Inject constructor(service: ApiConfiguration) : BaseViewModel() {

  private val nextMatchRepository = NextMatchRepository(service)
  private val leagueRepository = LeagueRepository(service)
  private val teamRepository = TeamRepository(service)

  var listLeagues = MutableLiveData<List<League>>()
  var selectedLeague = MutableLiveData<String>()
  var selectedIdLeague = MutableLiveData<String>()

  var listPastMatch = MutableLiveData<NextMatchResponse>()

  var teamModel = MutableLiveData<TeamResponse>()

  var matchModel = mutableListOf<MatchModel>()

  var isLoading = MutableLiveData<Boolean>(true)

  fun loadSoccerLeague() {
    addToDisposable(leagueRepository.loadLeagueList()
          .subscribe({ leagueResponse ->
            listLeagues.value = leagueResponse
          }, {

          }))
  }

  fun loadNextMatch(id: String? = Constant.ID_ENGLISH_PREMIER_LEAGUE) {
    addToDisposable(nextMatchRepository.loadNextMatch(id)
          .subscribe({ nextMatchResponse ->
            matchModel.clear()

            nextMatchResponse.events?.forEach { event ->
              mappingTeam(event, nextMatchResponse)
            }

          }, {

          }))
  }

  private fun mappingTeam(event: Event, nextMatchResponse: NextMatchResponse) {
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
            listPastMatch.value = nextMatchResponse
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