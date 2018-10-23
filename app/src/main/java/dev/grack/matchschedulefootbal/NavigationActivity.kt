package dev.grack.matchschedulefootbal

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.TabLayout
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import dev.grack.matchschedulefootbal.activity.league.LeagueActivity
import dev.grack.matchschedulefootbal.activity.searchactivity.SearchActivity
import dev.grack.matchschedulefootbal.activity.searchteams.SearchTeamActivity
import dev.grack.matchschedulefootbal.adapter.TabPageAdapter
import kotlinx.android.synthetic.main.activity_navigation.*
import kotlinx.android.synthetic.main.app_bar_navigation.*


class NavigationActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout
    private lateinit var tabPageAdapter: TabPageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        setSupportActionBar(toolbar)

        viewPager = view_pager_draw

        tabPageAdapter = TabPageAdapter(supportFragmentManager)
        viewPager.adapter = tabPageAdapter

        tabLayout = tabs_draw
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.getTabAt(0)?.setIcon(R.drawable.ic_calendar)
        tabLayout.getTabAt(1)?.setIcon(R.drawable.ic_trophy)
        tabLayout.getTabAt(2)?.setIcon(R.drawable.ic_favorite)
        tabLayout.getTabAt(3)?.setIcon(R.drawable.ic_fan)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_list_all_teams -> {
                startActivity(Intent(this, LeagueActivity::class.java))
            }
            R.id.nav_search_match -> {
                startActivity(Intent(this, SearchActivity::class.java))
            }
            R.id.nav_search_team -> {
                startActivity(Intent(this, SearchTeamActivity::class.java))
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
