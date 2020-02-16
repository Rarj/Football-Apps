package dev.grack.zmatchschedulefootbal.old.activity.pastmatch

import dev.grack.zmatchschedulefootbal.network.ApiClient
import dev.grack.zmatchschedulefootbal.network.ApiInterface
import org.junit.Assert
import org.junit.Test

class PastMatchPresenterTest {

    @Test
    fun getTeamList() {
        val apiService: ApiInterface = ApiClient.getRetrofit().create(ApiInterface::class.java)
        Assert.assertEquals(
                "https://www.thesportsdb.com/api/v1/json/1/eventspastleague.php?id=4328",
                apiService.getPastMatch("4328").request().url().toString())
    }
}