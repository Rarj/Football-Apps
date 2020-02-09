package dev.grack.features.parent

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.android.AndroidInjection
import dev.grack.base.BaseActivity
import dev.grack.features.nextmatch.NextMatchFragment
import dev.grack.features.pastmatch.PastMatchFragment
import dev.grack.zmatchschedulefootbal.R
import dev.grack.zmatchschedulefootbal.databinding.ActivityParentBinding

class ParentActivity : BaseActivity<ActivityParentBinding, ParentViewModel>() {

  private val pastFragment = PastMatchFragment()
  private val nextFragment = NextMatchFragment()
  private var fragmentManager = supportFragmentManager
  private var fragment: Fragment? = pastFragment

  override fun initInjector() {
    AndroidInjection.inject(this)
  }

  override fun setViewModel(): ParentViewModel? {
    viewModel = ViewModelProvider(this, viewModelFactory).get(ParentViewModel::class.java)
    return viewModel
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    bindView(R.layout.activity_parent)
    binding?.viewModel = viewModel
    binding?.lifecycleOwner = this

    binding?.bottomNavigation?.itemIconTintList = null

    initFragment()
    initNavigation()
  }

  private fun initFragment() {
    fragmentManager.beginTransaction().add(R.id.container_fragment, pastFragment, "pastFragment").commit()
    fragmentManager.beginTransaction().add(R.id.container_fragment, nextFragment, "nextFragment").hide(nextFragment).commit()
  }

  private fun loadFragment(activeFragment: Fragment): Boolean {
    fragmentManager.beginTransaction().hide(this.fragment!!).show(activeFragment).commit()
    this.fragment = activeFragment

    return true
  }

  private fun initNavigation() {
    binding?.bottomNavigation?.setOnNavigationItemSelectedListener { menuItem ->
      when (menuItem.itemId) {
        R.id.navigation_past_match -> {
          loadFragment(pastFragment)
        }
        R.id.navigation_next_match -> {
          loadFragment(nextFragment)
        }
      }

      true
    }
  }

}