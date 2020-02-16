package dev.grack.zmatchschedulefootbal.old.activity.detail

import dev.grack.zmatchschedulefootbal.model.Event
import dev.grack.zmatchschedulefootbal.model.EventsResponse
import dev.grack.zmatchschedulefootbal.model.Team
import dev.grack.zmatchschedulefootbal.model.TeamsResponse
import dev.grack.zmatchschedulefootbal.network.ApiClient
import dev.grack.zmatchschedulefootbal.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPresenter(private val view: DetailView) {
    fun getDetailList(idEvent: String?) {
        view.showLoading()
        val api: ApiInterface = ApiClient.getRetrofit().create(ApiInterface::class.java)
        val call = api.getDetailTeam(idEvent.toString())
        call.enqueue(object : Callback<EventsResponse> {
            override fun onResponse(call: Call<EventsResponse>, response: Response<EventsResponse>) {
                if (response.isSuccessful) {
                    val event: List<Event> = response.body()?.events!!
                    view.showDetailTeam(event)
                }
                view.hideLoading()
            }

            override fun onFailure(call: Call<EventsResponse>, t: Throwable) {

            }
        })
    }

    fun getImageHome(idHome: String) {
        val apiSearch = ApiClient.getRetrofit().create(ApiInterface::class.java)
        val call = apiSearch.getIdTeamHome(idHome)
        call.enqueue(object : Callback<TeamsResponse> {
            override fun onResponse(call: Call<TeamsResponse>, response: Response<TeamsResponse>) {
                if (response.isSuccessful) {
                    val teams: List<Team> = response.body()?.teams!!
                    view.showImageHomeTeam(teams)
                }
            }

            override fun onFailure(call: Call<TeamsResponse>, t: Throwable) {}
        })
    }

    fun getImageAway(idAway: String) {
        val apiSearch = ApiClient.getRetrofit().create(ApiInterface::class.java)
        val call = apiSearch.getIdTeamAway(idAway)
        call.enqueue(object : Callback<TeamsResponse> {
            override fun onResponse(call: Call<TeamsResponse>, response: Response<TeamsResponse>) {
                if (response.isSuccessful) {
                    val teams: List<Team> = response.body()?.teams!!
                    view.showImageAwayTeam(teams)
                }
                view.hideLoading()
            }

            override fun onFailure(call: Call<TeamsResponse>, t: Throwable) {}
        })
    }
}