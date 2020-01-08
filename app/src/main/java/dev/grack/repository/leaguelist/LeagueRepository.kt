package dev.grack.repository.leaguelist

import dev.grack.network.ApiConfiguration
import dev.grack.repository.leaguelist.model.League
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Singleton
class LeagueRepository(private val service: ApiConfiguration) {

  fun loadLeagueList(): Observable<List<League>> {
    return service.getLeagueList()
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .map { leaguesResponse ->
            leaguesResponse.leagues.filter { league ->
              league.strSport == "Soccer"
            }
          }
  }

}