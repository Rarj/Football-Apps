package dev.grack.zmatchschedulefootbal

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import dev.grack.zmatchschedulefootbal.activity.league.LeagueActivity
import dev.grack.zmatchschedulefootbal.activity.searchactivity.SearchActivity
import dev.grack.zmatchschedulefootbal.activity.searchteams.SearchTeamActivity
import dev.grack.zmatchschedulefootbal.adapter.TabPageAdapter
import kotlinx.android.synthetic.main.activity_navigation.*
import kotlinx.android.synthetic.main.app_bar_navigation.*


class NavigationActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

  private lateinit var viewPager: androidx.viewpager.widget.ViewPager
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
