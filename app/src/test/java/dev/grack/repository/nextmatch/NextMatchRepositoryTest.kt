package dev.grack.repository.nextmatch

import dev.grack.Constant.ID_ENGLISH_PREMIER_LEAGUE
import dev.grack.repository.nextmatch.model.Event
import dev.grack.repository.nextmatch.model.NextMatchResponse
import io.reactivex.Observable
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class NextMatchRepositoryTest {

  lateinit var repository: NextMatchRepository

  private fun `mapping event model`(): NextMatchResponse {
    return NextMatchResponse(
        arrayListOf(Event("", "", "", "", "", "", "", "", "", "", "", ""))
    )

//    return Event(
//          "dateEvent",
//          "dateEventLocal",
//          "idAPIfootball",
//          "idAwayTeam",
//          "idEvent",
//          "idHomeTeam",
//          "idLeague",
//          "idSoccerXML",
//          "intAwayScore",
//          "intAwayShots",
//          "intHomeScore",
//          "intHomeShots",
//          "intRound",
//          "intSpectators",
//          "strAwayFormation",
//          "strAwayGoalDetails",
//          "strAwayLineupDefense",
//          "strAwayLineupForward",
//          "strAwayLineupGoalkeeper",
//          "strAwayLineupMidfield",
//          "strAwayLineupSubstitutes",
//          "strAwayRedCards",
//          "strAwayTeam",
//          "strAwayYellowCards",
//          "strBanner",
//          "strCircuit",
//          "strCity",
//          "strCountry",
//          "strDate",
//          "strDescriptionEN",
//          "strEvent",
//          "strEventAlternate",
//          "strFanart",
//          "strFilename",
//          "strHomeFormation",
//          "strHomeGoalDetails",
//          "strHomeLineupDefense",
//          "strHomeLineupForward",
//          "strHomeLineupGoalkeeper",
//          "strHomeLineupMidfield",
//          "strHomeLineupSubstitutes",
//          "strHomeRedCards",
//          "strHomeTeam",
//          "strHomeYellowCards",
//          "strLeague",
//          "strLocked",
//          "strMap",
//          "strPoster",
//          "strResult",
//          "strSeason",
//          "strSport",
//          "strTVStation",
//          "strThumb",
//          "strTime",
//          "strTimeLocal",
//          "strTweet1",
//          "strTweet2",
//          "strTweet3",
//          "strVideo"
//    )
  }

  @Before
  fun setUp() {
    repository = mock(NextMatchRepository::class.java)
  }

  @Test
  fun loadNextMatch() {
    var eventModel = `mapping event model`()
    val observable: Observable<NextMatchResponse> = Observable.just(`mapping event model`())
    `when`(repository.loadNextMatch("4")).thenReturn(observable)

    repository.loadNextMatch(ID_ENGLISH_PREMIER_LEAGUE)
        .subscribe {

          //            eventModel = it

//            it.events.forEach { event ->
//              eventModel = Event(
//                    event.dateEvent,
//                    event.dateEventLocal,
//                    event.idAPIfootball,
//                    event.idAwayTeam,
//                    event.idEvent,
//                    event.idHomeTeam,
//                    event.idLeague,
//                    event.idSoccerXML,
//                    event.intAwayScore,
//                    event.intAwayShots,
//                    event.intHomeScore,
//                    event.intHomeShots,
//                    event.intRound,
//                    event.intSpectators,
//                    event.strAwayFormation,
//                    event.strAwayGoalDetails,
//                    event.strAwayLineupDefense,
//                    event.strAwayLineupForward,
//                    event.strAwayLineupGoalkeeper,
//                    event.strAwayLineupMidfield,
//                    event.strAwayLineupSubstitutes,
//                    event.strAwayRedCards,
//                    event.strAwayTeam,
//                    event.strAwayYellowCards,
//                    event.strBanner,
//                    event.strCircuit,
//                    event.strCity,
//                    event.strCountry,
//                    event.strDate,
//                    event.strDescriptionEN,
//                    event.strEvent,
//                    event.strEventAlternate,
//                    event.strFanart,
//                    event.strFilename,
//                    event.strHomeFormation,
//                    event.strHomeGoalDetails,
//                    event.strHomeLineupDefense,
//                    event.strHomeLineupForward,
//                    event.strHomeLineupGoalkeeper,
//                    event.strHomeLineupMidfield,
//                    event.strHomeLineupSubstitutes,
//                    event.strHomeRedCards,
//                    event.strHomeTeam,
//                    event.strHomeYellowCards,
//                    event.strLeague,
//                    event.strLocked,
//                    event.strMap,
//                    event.strPoster,
//                    event.strResult,
//                    event.strSeason,
//                    event.strSport,
//                    event.strTVStation,
//                    event.strThumb,
//                    event.strTime,
//                    event.strTimeLocal,
//                    event.strTweet1,
//                    event.strTweet2,
//                    event.strTweet3,
//                    event.strVideo
//              )
//            }
        }

    Assert.assertEquals(`mapping event model`(), eventModel)
  }
}