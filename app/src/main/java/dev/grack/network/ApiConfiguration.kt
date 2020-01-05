package dev.grack.network

import dev.grack.repository.leaguelist.model.LeagueResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiConfiguration {

  @GET("all_leagues.php")
  fun getLeagueList(): Observable<LeagueResponse>

}