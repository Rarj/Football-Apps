package dev.grack.matchschedulefootbal.activity.detail

import dev.grack.matchschedulefootbal.network.ApiClient
import dev.grack.matchschedulefootbal.network.ApiInterface
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*

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