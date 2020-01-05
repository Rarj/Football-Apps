package dev.grack.zmatchschedulefootbal.activity.searchteams

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.miguelcatalan.materialsearchview.MaterialSearchView
import com.rw.loadingdialog.LoadingView
import dev.grack.zmatchschedulefootbal.R
import dev.grack.zmatchschedulefootbal.activity.detailteam.DetailTeamActivity
import dev.grack.zmatchschedulefootbal.adapter.SearchViewTeamAdapter
import dev.grack.zmatchschedulefootbal.model.TeamSearch
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.act
import org.jetbrains.anko.ctx
import org.jetbrains.anko.find

class SearchTeamActivity : AppCompatActivity(), SearchTeamView {
    private lateinit var searchView: MaterialSearchView
    private lateinit var presenter: SearchTeamPresenter
    private lateinit var mAdapter: SearchViewTeamAdapter
    private val events: MutableList<TeamSearch> = mutableListOf()
    private lateinit var recy: androidx.recyclerview.widget.RecyclerView
    private lateinit var loadingView: LoadingView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_team)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        searchView = find(R.id.search_material)
        recy = find(R.id.recycler_search)

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

        presenter = SearchTeamPresenter(this)


        searchView.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                presenter.getSearchTeam(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val item = menu.findItem(R.id.action_search)
        searchView.setMenuItem(item)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        return id == R.id.action_search || super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (searchView.isSearchOpen) {
            searchView.closeSearch()
        } else {
            super.onBackPressed()
        }
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

    override fun showSearchList(data: List<TeamSearch>) {
        events.clear()
        events.addAll(data)
        mAdapter.notifyDataSetChanged()
    }


}
