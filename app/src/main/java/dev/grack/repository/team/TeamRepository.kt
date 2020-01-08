package dev.grack.repository.team

import dev.grack.network.ApiConfiguration
import dev.grack.repository.team.model.TeamResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Singleton
class TeamRepository(val apiConfiguration: ApiConfiguration) {

  fun loadTeamDetail(id: String): Observable<TeamResponse> {
    return apiConfiguration.getTeamDetail(id)
          .observeOn(AndroidSchedulers.mainThread())
          .subscribeOn(Schedulers.io())
          .map {
            it
          }
  }

}