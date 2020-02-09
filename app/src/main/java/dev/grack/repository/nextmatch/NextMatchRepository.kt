package dev.grack.repository.nextmatch

import dev.grack.network.ApiConfiguration
import dev.grack.repository.nextmatch.model.NextMatchResponse
import dev.grack.repository.pastmatch.model.PastMatchResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Singleton
class NextMatchRepository(private val service: ApiConfiguration) {

  fun loadNextMatch(id: String?): Observable<NextMatchResponse> {
    return service.getNextEvent(id.toString())
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .map {
            it
          }
  }

}