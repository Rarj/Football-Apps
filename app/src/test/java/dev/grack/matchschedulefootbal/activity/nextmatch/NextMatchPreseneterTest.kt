package dev.grack.matchschedulefootbal.activity.nextmatch

import dev.grack.matchschedulefootbal.network.ApiClient
import dev.grack.matchschedulefootbal.network.ApiInterface
import org.junit.Assert
import org.junit.Test


class NextMatchPreseneterTest {
    @Test
    fun getTeamList() {
        val apiService: ApiInterface = ApiClient.getRetrofit().create(ApiInterface::class.java)
        Assert.assertEquals(
                "https://www.thesportsdb.com/api/v1/json/1/eventsnextleague.php?id=4328",
                apiService.getNextMatch("4328").request().url().toString())
    }
}