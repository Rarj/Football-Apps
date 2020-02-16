package dev.grack.zmatchschedulefootbal.old.activity.detail

import dev.grack.zmatchschedulefootbal.network.ApiClient
import dev.grack.zmatchschedulefootbal.network.ApiInterface
import org.junit.Assert
import org.junit.Test

class DetailPresenterTest {

    @Test
    fun getDetailList() {
        val id = "441613"
        val apiService: ApiInterface = ApiClient.getRetrofit().create(ApiInterface::class.java)
        Assert.assertEquals(
                "https://www.thesportsdb.com/api/v1/json/1/lookupevent.php?id=441613",
                apiService.getDetailTeam(id).request().url().toString())
    }

    @Test
    fun getImageHome() {
        val id = "133604"
        val apiService: ApiInterface = ApiClient.getRetrofit().create(ApiInterface::class.java)
        Assert.assertEquals(
                "https://www.thesportsdb.com/api/v1/json/1/lookupteam.php/preview?id=133604",
                apiService.getIdTeamHome(id).request().url().toString())
    }

    @Test
    fun getImageAway() {
        val id = "133604"
        val apiService: ApiInterface = ApiClient.getRetrofit().create(ApiInterface::class.java)
        Assert.assertEquals(
                "https://www.thesportsdb.com/api/v1/json/1/lookupteam.php/preview?id=133604",
                apiService.getIdTeamAway(id).request().url().toString())
    }
}