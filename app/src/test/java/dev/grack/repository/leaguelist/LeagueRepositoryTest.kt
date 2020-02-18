package dev.grack.repository.leaguelist

import dev.grack.repository.leaguelist.model.League
import io.reactivex.Observable
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class LeagueRepositoryTest {

  lateinit var repository: LeagueRepository

  private fun `mapping league soccer model`(): League {
    return League(
          "idLeague",
          "strLeague",
          "strLeagueAlternate",
          "Soccer")
  }

  @Before
  fun setUp() {
    repository = mock(LeagueRepository::class.java)
  }

  @Test
  fun loadLeagueList() {
    var leagueModel = League()
    val observable: Observable<List<League>> = Observable.just(arrayListOf(`mapping league soccer model`()))

    `when`(repository.loadLeagueList()).thenReturn(observable)

    repository.loadLeagueList()
          .subscribe { leagues ->
            leagues.forEach { league ->
              leagueModel = League(
                    league.idLeague,
                    league.strLeague,
                    league.strLeagueAlternate,
                    league.strSport)
            }
          }

    Assert.assertEquals(`mapping league soccer model`(), leagueModel)
  }
}