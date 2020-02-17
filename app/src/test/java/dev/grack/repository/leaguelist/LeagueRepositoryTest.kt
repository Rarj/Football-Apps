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

dateEvent = "dateEvent"
dateEventLocal = "dateEventLocal"
idAPIfootball = "idAPIfootball"
idAwayTeam = "idAwayTeam"
idEvent = "idEvent"
idHomeTeam = "idHomeTeam"
idLeague = "idLeague"
idSoccerXML = "idSoccerXML"
intAwayScore = "intAwayScore"
intAwayShots = "intAwayShots"
intHomeScore = "intHomeScore"
intHomeShots = "intHomeShots"
intRound = "intRound"
intSpectators = "intSpectators"
strAwayFormation = "strAwayFormation"
strAwayGoalDetails = "strAwayGoalDetails"
strAwayLineupDefense = "strAwayLineupDefense"
strAwayLineupForward = "strAwayLineupForward"
strAwayLineupGoalkeeper = "strAwayLineupGoalkeeper"
strAwayLineupMidfield = "strAwayLineupMidfield"
strAwayLineupSubstitutes = "strAwayLineupSubstitutes"
strAwayRedCards = "strAwayRedCards"
strAwayTeam = "strAwayTeam"
strAwayYellowCards = "strAwayYellowCards"
strBanner = "strBanner"
strCircuit = "strCircuit"
strCity = "strCity"
strCountry = "strCountry"
strDate = "strDate"
strDescriptionEN = "strDescriptionEN"
strEvent = "strEvent"
strEventAlternate = "strEventAlternate"
strFanart = "strFanart"
strFilename = "strFilename"
strHomeFormation = "strHomeFormation"
strHomeGoalDetails = "strHomeGoalDetails"
strHomeLineupDefense = "strHomeLineupDefense"
strHomeLineupForward = "strHomeLineupForward"
strHomeLineupGoalkeeper = "strHomeLineupGoalkeeper"
strHomeLineupMidfield = "strHomeLineupMidfield"
strHomeLineupSubstitutes = "strHomeLineupSubstitutes"
strHomeRedCards = "strHomeRedCards"
strHomeTeam = "strHomeTeam"
strHomeYellowCards = "strHomeYellowCards"
strLeague = "strLeague"
strLocked = "strLocked"
strMap = "strMap"
strPoster = "strPoster"
strResult = "strResult"
strSeason = "strSeason"
strSport = "strSport"
strTVStation = "strTVStation"
strThumb = "strThumb"
strTime = "strTime"
strTimeLocal = "strTimeLocal"
strTweet1 = "strTweet1"
strTweet2 = "strTweet2"
strTweet3 = "strTweet3"
strVideo = "strVideo"