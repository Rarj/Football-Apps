package dev.grack.zmatchschedulefootbal.activity.league

import dev.grack.zmatchschedulefootbal.model.League
import dev.grack.zmatchschedulefootbal.model.LeagueResponse
import dev.grack.zmatchschedulefootbal.model.TeamSearch
import dev.grack.zmatchschedulefootbal.model.TeamSearchResponse
import dev.grack.zmatchschedulefootbal.network.ApiClient
import dev.grack.zmatchschedulefootbal.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LeaguePresenter (private val view: LeagueView) {
    fun getLeagueList() {
        val api: ApiInterface = ApiClient.getRetrofit().create(ApiInterface::class.java)
        val call = api.getLeague()
        call.enqueue(object : Callback<LeagueResponse> {
            override fun onResponse(call: Call<LeagueResponse>, response: Response<LeagueResponse>) {
                if (response.isSuccessful) {
                    val event: List<League> = response.body()?.leagues!!
                    view.showLeague(event)
                }
            }

            override fun onFailure(call: Call<LeagueResponse>, t: Throwable) {

            }
        })
    }

    fun getLeagueList(query: String) {
        view.showLoading()
        val api: ApiInterface = ApiClient.getRetrofit().create(ApiInterface::class.java)
        val call = api.getLeagueTeam(query)
        call.enqueue(object : Callback<TeamSearchResponse> {
            override fun onResponse(call: Call<TeamSearchResponse>, response: Response<TeamSearchResponse>) {
                if (response.isSuccessful) {
                    val event: List<TeamSearch>? = response.body()?.teams
                    event?.let { view.showLeagueList(it) }
                }
                view.hideLoading()
            }

            override fun onFailure(call: Call<TeamSearchResponse>, t: Throwable) {

            }
        })
    }
}