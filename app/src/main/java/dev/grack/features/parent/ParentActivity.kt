package dev.grack.features.parent

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.android.AndroidInjection
import dev.grack.features.nextmatch.NextMatchFragment
import dev.grack.features.pastmatch.PastMatchFragment
import dev.grack.zmatchschedulefootbal.R
import dev.grack.zmatchschedulefootbal.databinding.ActivityParentBinding
import javax.inject.Inject

class ParentActivity : AppCompatActivity() {

  @Inject
  internal lateinit var viewModelFactory: ViewModelProvider.Factory

  private lateinit var viewModel: ParentViewModel
  private lateinit var binding: ActivityParentBinding

  private val pastFragment = PastMatchFragment()
  private val nextFragment = NextMatchFragment()
  private var fragmentManager = supportFragmentManager
  private var fragment: Fragment? = pastFragment

  @SuppressLint("CheckResult")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    AndroidInjection.inject(this)

    viewModel = ViewModelProvider(this, viewModelFactory).get(ParentViewModel::class.java)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_parent)
    binding.viewModel = viewModel
    binding.lifecycleOwner = this

    binding.bottomNavigation.itemIconTintList = null

    initiateFragment()

    binding.bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
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

  private fun initiateFragment() {
    fragmentManager.beginTransaction().add(R.id.container_fragment, pastFragment, "pastFragment").commit()
    fragmentManager.beginTransaction().add(R.id.container_fragment, nextFragment, "nextFragment").hide(nextFragment).commit()
  }

  private fun loadFragment(activeFragment: Fragment): Boolean {
    fragmentManager.beginTransaction().hide(this.fragment!!).show(activeFragment).commit()
    this.fragment = activeFragment

    return true
  }

}