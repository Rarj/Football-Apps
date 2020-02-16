package dev.grack.zmatchschedulefootbal.old.activity.searchactivity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.miguelcatalan.materialsearchview.MaterialSearchView
import com.rw.loadingdialog.LoadingView
import dev.grack.zmatchschedulefootbal.R
import dev.grack.zmatchschedulefootbal.old.activity.detail.DetailActivity
import dev.grack.zmatchschedulefootbal.adapter.SearchViewAdapter
import dev.grack.zmatchschedulefootbal.model.EventSearch
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.act
import org.jetbrains.anko.ctx
import org.jetbrains.anko.find

class SearchActivity : AppCompatActivity(), SearchView {
    private lateinit var searchView: MaterialSearchView
    private lateinit var presenter: SearchPresenter
    private lateinit var mAdapter: SearchViewAdapter
    private val events: MutableList<EventSearch> = mutableListOf()
    private lateinit var recy: androidx.recyclerview.widget.RecyclerView
    private lateinit var loadingView: LoadingView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        searchView = find(R.id.search_material)
        recy = find(R.id.recycler_search)

        mAdapter = SearchViewAdapter(events) {
            val intent = Intent(ctx, DetailActivity::class.java)
            val bundle = Bundle()
            bundle.putString("id", it.idEvent)
            bundle.putString("idhome", it.idHomeTeam)
            bundle.putString("idaway", it.idAwayTeam)
            intent.putExtra(DetailActivity.POSITIONEXTRA, bundle)
            startActivity(intent)
        }

        recy.adapter = mAdapter
        recy.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)

        presenter = SearchPresenter(this)

        searchView.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                presenter.getSearchMatch(query)
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

    override fun showSearchList(data: List<EventSearch>) {
        events.clear()
        events.addAll(data)
        mAdapter.notifyDataSetChanged()
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
}
