package dev.grack.zmatchschedulefootbal.network

import dev.grack.zmatchschedulefootbal.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
  @GET("api/v1/json/1/eventsnextleague.php")
  fun getNextMatch(@Query("id") id: String): Call<EventsResponse>

  @GET("api/v1/json/1/eventspastleague.php")
  fun getPastMatch(@Query("id") id: String): Call<EventsResponse>

  @GET("api/v1/json/1/lookupteam.php/preview")
  fun getIdTeamHome(@Query("id") id: String): Call<TeamsResponse>

  @GET("api/v1/json/1/lookupteam.php/preview")
  fun getIdTeamAway(@Query("id") id: String): Call<TeamsResponse>

  @GET("api/v1/json/1/lookupteam.php")
  fun getLookupTeam(@Query("id") id: String): Call<TeamSearchResponse>

  @GET("api/v1/json/1/lookupevent.php")
  fun getDetailTeam(@Query("id") id: String): Call<EventsResponse>

  @GET("api/v1/json/1/searchevents.php")
  fun getSearchMatch(@Query("e") e: String): Call<EventSearchRespone>

  @GET("api/v1/json/1/searchteams.php")
  fun getSearchTeam(@Query("t") t: String): Call<TeamSearchResponse>

  @GET("api/v1/json/1/lookup_all_players.php")
  fun getPlayerTeam(@Query("id") id: String): Call<PlayerResponse>

  @GET("api/v1/json/1/search_all_teams.php")
  fun getLeagueTeam(@Query("l") l: String): Call<TeamSearchResponse>

  @GET("api/v1/json/1/all_leagues.php")
  fun getLeague(): Call<LeagueResponse>
}