package dev.grack.zmatchschedulefootbal.activity.league

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jaredrummler.materialspinner.MaterialSpinner
import com.rw.loadingdialog.LoadingView
import dev.grack.zmatchschedulefootbal.R
import dev.grack.zmatchschedulefootbal.activity.detailteam.DetailTeamActivity
import dev.grack.zmatchschedulefootbal.adapter.SearchViewTeamAdapter
import dev.grack.zmatchschedulefootbal.model.League
import dev.grack.zmatchschedulefootbal.model.TeamSearch
import org.jetbrains.anko.act
import org.jetbrains.anko.ctx
import org.jetbrains.anko.find

class LeagueActivity : AppCompatActivity(), LeagueView {
  private lateinit var spinner: MaterialSpinner
  private lateinit var dataLeague: ArrayList<String>
  private lateinit var presenter: LeaguePresenter
  private lateinit var mAdapter: SearchViewTeamAdapter
  private val events: MutableList<TeamSearch> = mutableListOf()
  private lateinit var recy: androidx.recyclerview.widget.RecyclerView
  private lateinit var loadingView: LoadingView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_league)

    presenter = LeaguePresenter(this)
    dataLeague = ArrayList()

    spinner = this.find(R.id.spinner)
    recy = this.find(R.id.recycler_league)

    mAdapter = SearchViewTeamAdapter(events) {
      val intent = Intent(ctx, DetailTeamActivity::class.java)
      val bundle = Bundle()
      bundle.putString("id", it.idTeam)
      bundle.putString("teamBadge", it.strTeamBadge)
      intent.putExtra(DetailTeamActivity.POSITIONEXTRA, bundle)
      startActivity(intent)
    }

    recy.adapter = mAdapter
    recy.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)

    presenter = LeaguePresenter(this)
    presenter.getLeagueList()
    presenter.getLeagueList(getString(R.string.english_premier_league))

    selectSpinner()
  }

  private fun selectSpinner() {
    spinner.setOnItemSelectedListener { _, _, _, item ->
      presenter.getLeagueList(item.toString())
    }
  }

  override fun showLeague(data: List<League>) {
    for (item: League in data.iterator()) {
      dataLeague.add(item.strLeague.toString())
    }
    spinner.setItems(dataLeague)
  }

  override fun showLoading() {
    loadingView = LoadingView.Builder(act)
          .setProgressColorResource(R.color.night_shadz_b83b5e)
          .setProgressStyle(LoadingView.ProgressStyle.CYCLIC)
          .attachTo(act)
    loadingView.show()
  }

  override fun hideLoading() {
    loadingView.hide()
  }

  override fun showLeagueList(data: List<TeamSearch>) {
    events.clear()
    events.addAll(data)
    mAdapter.notifyDataSetChanged()
  }

}
