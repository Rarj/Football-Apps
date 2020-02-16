package dev.grack.zmatchschedulefootbal.old.activity.nextmatch

import dev.grack.zmatchschedulefootbal.model.Event
import dev.grack.zmatchschedulefootbal.model.EventsResponse
import dev.grack.zmatchschedulefootbal.model.League
import dev.grack.zmatchschedulefootbal.model.LeagueResponse
import dev.grack.zmatchschedulefootbal.network.ApiClient
import dev.grack.zmatchschedulefootbal.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NextMatchPreseneter(private val view: NextMatchView) {
    fun getTeamList(id: String) {
        view.showLoading()
        val api: ApiInterface = ApiClient.getRetrofit().create(ApiInterface::class.java)
        val call = api.getNextMatch(id)
        call.enqueue(object : Callback<EventsResponse> {
            override fun onResponse(call: Call<EventsResponse>, response: Response<EventsResponse>) {
                if (response.isSuccessful) {
                    val event: List<Event> = response.body()?.events!!
                    view.showTeamList(event)
                }
                view.hideLoading()
            }

            override fun onFailure(call: Call<EventsResponse>, t: Throwable) {

            }
        })
    }

    fun getAllLeagues(){
        val api: ApiInterface = ApiClient.getRetrofit().create(ApiInterface::class.java)
        val call = api.getLeague()
        call.enqueue(object : Callback<LeagueResponse> {
            override fun onResponse(call: Call<LeagueResponse>, response: Response<LeagueResponse>) {
                if (response.isSuccessful) {
                    val event: List<League> = response.body()?.leagues!!
                    view.showLeagueList(event)
                }
            }

            override fun onFailure(call: Call<LeagueResponse>, t: Throwable) {

            }
        })
    }
}