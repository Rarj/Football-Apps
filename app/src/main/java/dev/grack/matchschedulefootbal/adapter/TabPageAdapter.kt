package dev.grack.matchschedulefootbal.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import dev.grack.matchschedulefootbal.activity.favorite.FavoriteFragment
import dev.grack.matchschedulefootbal.activity.favoriteteam.FavoriteTeamFragment
import dev.grack.matchschedulefootbal.activity.nextmatch.NextMatchFragment
import dev.grack.matchschedulefootbal.activity.pastmatch.PastMatchFragment

class TabPageAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {

    companion object {
        const val PAGE_COUNT = 4
    }

    private val titleTab = arrayOf("Next Match", "Past Match", "Favorited Matches", "Favorited Teams")

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> {
                NextMatchFragment()
            }
            1 -> {
                PastMatchFragment()
            }
            2 -> {
                FavoriteFragment()
            }
            3 -> {
                FavoriteTeamFragment()
            }
            else -> {
                null
            }
        }
    }

    override fun getCount(): Int {
        return PAGE_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titleTab[position]
    }
}