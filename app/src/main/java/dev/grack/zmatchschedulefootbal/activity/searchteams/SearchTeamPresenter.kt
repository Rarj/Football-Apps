package dev.grack.zmatchschedulefootbal.activity.searchteams

import dev.grack.zmatchschedulefootbal.model.TeamSearchResponse
import dev.grack.zmatchschedulefootbal.model.TeamSearch
import dev.grack.zmatchschedulefootbal.network.ApiClient
import dev.grack.zmatchschedulefootbal.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchTeamPresenter(private val view: SearchTeamView) {
    fun getSearchTeam(query: String) {
        view.showLoading()
        val api: ApiInterface = ApiClient.getRetrofit().create(ApiInterface::class.java)
        val call = api.getSearchTeam(query)
        call.enqueue(object : Callback<TeamSearchResponse> {
            override fun onResponse(call: Call<TeamSearchResponse>, response: Response<TeamSearchResponse>) {
                if (response.isSuccessful) {
                    val event: List<TeamSearch>? = response.body()?.teams
                    event?.let { view.showSearchList(it) }
                }
                view.hideLoading()
            }

            override fun onFailure(call: Call<TeamSearchResponse>, t: Throwable) {

            }
        })
    }
}