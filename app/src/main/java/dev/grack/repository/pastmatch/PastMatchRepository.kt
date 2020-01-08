package dev.grack.repository.pastmatch

import dev.grack.network.ApiConfiguration
import dev.grack.repository.pastmatch.model.PastMatchResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Singleton
class PastMatchRepository(private val service: ApiConfiguration) {

  fun loadPastMatch(id: String?): Observable<PastMatchResponse> {
    return service.getPastEvent(id.toString())
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .map {
            it
          }
  }

}