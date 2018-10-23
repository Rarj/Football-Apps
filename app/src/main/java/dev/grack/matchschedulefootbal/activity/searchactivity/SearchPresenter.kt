package dev.grack.matchschedulefootbal.activity.searchactivity

import dev.grack.matchschedulefootbal.model.EventSearch
import dev.grack.matchschedulefootbal.model.EventSearchRespone
import dev.grack.matchschedulefootbal.network.ApiClient
import dev.grack.matchschedulefootbal.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchPresenter(private val view: SearchView) {
    fun getSearchMatch(query: String) {
        view.showLoading()
        val api: ApiInterface = ApiClient.getRetrofit().create(ApiInterface::class.java)
        val call = api.getSearchMatch(query)
        call.enqueue(object : Callback<EventSearchRespone> {
            override fun onResponse(call: Call<EventSearchRespone>, response: Response<EventSearchRespone>) {
                if (response.isSuccessful) {
                    val event: List<EventSearch>? = response.body()?.event
                    event?.let { view.showSearchList(it) }
                }
                view.hideLoading()
            }

            override fun onFailure(call: Call<EventSearchRespone>, t: Throwable) {

            }
        })
    }
}