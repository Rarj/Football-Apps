package dev.grack.network

import dev.grack.repository.leaguelist.model.LeagueResponse
import dev.grack.repository.pastmatch.model.PastMatchResponse
import dev.grack.repository.team.model.TeamResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiConfiguration {

  @GET("all_leagues.php")
  fun getLeagueList(): Observable<LeagueResponse>

  @GET("eventspastleague.php")
  fun getPastEvent(@Query("id") id: String): Observable<PastMatchResponse>

  @GET("lookupteam.php")
  fun getTeamDetail(@Query("id") id: String): Observable<TeamResponse>


}