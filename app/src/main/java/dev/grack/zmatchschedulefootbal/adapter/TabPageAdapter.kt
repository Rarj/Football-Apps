package dev.grack.zmatchschedulefootbal.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import dev.grack.zmatchschedulefootbal.activity.favorite.FavoriteFragment
import dev.grack.zmatchschedulefootbal.activity.favoriteteam.FavoriteTeamFragment
import dev.grack.zmatchschedulefootbal.activity.nextmatch.NextMatchFragment
import dev.grack.zmatchschedulefootbal.activity.pastmatch.PastMatchFragment

class TabPageAdapter(fm: FragmentManager) : androidx.fragment.app.FragmentPagerAdapter(fm) {

  companion object {
    const val PAGE_COUNT = 4
  }

  private val titleTab = arrayOf("Next Match", "Past Match", "Favorited Matches", "Favorited Teams")

  override fun getItem(position: Int): Fragment {
    if (position == 0) {
      NextMatchFragment()
    } else if (position == 1) {
      PastMatchFragment()
    } else if (position == 2) {
      FavoriteFragment()
    } else if (position == 3) {
      FavoriteTeamFragment()
    }

    return NextMatchFragment()
  }

  override fun getCount(): Int {
    return PAGE_COUNT
  }

  override fun getPageTitle(position: Int): CharSequence? {
    return titleTab[position]
  }
}