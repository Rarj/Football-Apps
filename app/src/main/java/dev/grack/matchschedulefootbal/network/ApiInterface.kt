package dev.grack.matchschedulefootbal.network

import dev.grack.matchschedulefootbal.BuildConfig
import dev.grack.matchschedulefootbal.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("api/v1/json/${BuildConfig.TSDB_API_KEY}/eventsnextleague.php")
    fun getNextMatch(@Query("id") id: String): Call<EventsResponse>

    @GET("api/v1/json/${BuildConfig.TSDB_API_KEY}/eventspastleague.php")
    fun getPastMatch(@Query("id") id: String): Call<EventsResponse>

    @GET("api/v1/json/${BuildConfig.TSDB_API_KEY}/lookupteam.php/preview")
    fun getIdTeamHome(@Query("id") id: String): Call<TeamsResponse>

    @GET("api/v1/json/${BuildConfig.TSDB_API_KEY}/lookupteam.php/preview")
    fun getIdTeamAway(@Query("id") id: String): Call<TeamsResponse>

    @GET("api/v1/json/${BuildConfig.TSDB_API_KEY}/lookupteam.php")
    fun getLookupTeam(@Query("id") id: String): Call<TeamSearchResponse>

    @GET("api/v1/json/${BuildConfig.TSDB_API_KEY}/lookupevent.php")
    fun getDetailTeam(@Query("id") id: String): Call<EventsResponse>

    @GET("api/v1/json/${BuildConfig.TSDB_API_KEY}/searchevents.php")
    fun getSearchMatch(@Query("e") e: String): Call<EventSearchRespone>

    @GET("api/v1/json/${BuildConfig.TSDB_API_KEY}/searchteams.php")
    fun getSearchTeam(@Query("t") t: String): Call<TeamSearchResponse>

    @GET("api/v1/json/${BuildConfig.TSDB_API_KEY}/lookup_all_players.php")
    fun getPlayerTeam(@Query("id") id: String): Call<PlayerResponse>

    @GET("api/v1/json/${BuildConfig.TSDB_API_KEY}/search_all_teams.php")
    fun getLeagueTeam(@Query("l") l: String): Call<TeamSearchResponse>

    @GET("api/v1/json/${BuildConfig.TSDB_API_KEY}/all_leagues.php")
    fun getLeague(): Call<LeagueResponse>
}