package dev.grack.matchschedulefootbal.activity.detailteam

import dev.grack.matchschedulefootbal.model.TeamSearch
import dev.grack.matchschedulefootbal.model.TeamSearchResponse
import dev.grack.matchschedulefootbal.network.ApiClient
import dev.grack.matchschedulefootbal.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailTeamPresenter(private val view: DetailTeamView) {
    fun getDetailTeamList(idEvent: String?) {
        val api: ApiInterface = ApiClient.getRetrofit().create(ApiInterface::class.java)
        val call = api.getLookupTeam(idEvent.toString())
        call.enqueue(object : Callback<TeamSearchResponse> {
            override fun onResponse(call: Call<TeamSearchResponse>, response: Response<TeamSearchResponse>) {
                if (response.isSuccessful) {
                    val event: List<TeamSearch> = response.body()?.teams!!
                    view.showDetailTeams(event)
                }
            }

            override fun onFailure(call: Call<TeamSearchResponse>, t: Throwable) {

            }
        })
    }
}