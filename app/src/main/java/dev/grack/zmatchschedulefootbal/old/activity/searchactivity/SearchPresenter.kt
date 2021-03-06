package dev.grack.zmatchschedulefootbal.old.activity.searchactivity

import dev.grack.zmatchschedulefootbal.model.EventSearch
import dev.grack.zmatchschedulefootbal.model.EventSearchRespone
import dev.grack.zmatchschedulefootbal.network.ApiClient
import dev.grack.zmatchschedulefootbal.network.ApiInterface
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