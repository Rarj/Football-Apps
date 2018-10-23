package dev.grack.matchschedulefootbal.activity.player

import dev.grack.matchschedulefootbal.model.Player
import dev.grack.matchschedulefootbal.model.PlayerResponse
import dev.grack.matchschedulefootbal.network.ApiClient
import dev.grack.matchschedulefootbal.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlayerPresenter (private val view: PlayerView) {
    fun getPlayer(id: String) {
        view.showLoading()
        val api: ApiInterface = ApiClient.getRetrofit().create(ApiInterface::class.java)
        val call = api.getPlayerTeam(id)
        call.enqueue(object : Callback<PlayerResponse> {
            override fun onResponse(call: Call<PlayerResponse>, response: Response<PlayerResponse>) {
                if (response.isSuccessful) {
                    val event: List<Player>? = response.body()?.player
                    event?.let { view.showSearchList(it) }
                }
                view.hideLoading()
            }

            override fun onFailure(call: Call<PlayerResponse>, t: Throwable) {

            }
        })
    }
}