package dev.grack.features.pastmatch

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.AndroidSupportInjection
import dev.grack.zmatchschedulefootbal.R
import dev.grack.zmatchschedulefootbal.databinding.PastMatchFragmentBinding
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PastMatchFragment : Fragment() {

  @Inject
  lateinit var viewModeFactory: ViewModelProvider.Factory

  private lateinit var viewModel: PastMatchViewModel
  private lateinit var binding: PastMatchFragmentBinding

  override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
  ): View? {

    AndroidSupportInjection.inject(this)

    viewModel = ViewModelProviders.of(this, viewModeFactory).get(PastMatchViewModel::class.java)
    binding = DataBindingUtil.inflate(
          inflater,
          R.layout.past_match_fragment,
          container,
          false)

    showOrHideFilter(null)

    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    viewModel.loadSoccerLeague()

    viewModel.listLeagues.observe(this, Observer { listLeagues ->
      binding.progressCircular.visibility = GONE
      showOrHideFilter(ContextCompat.getDrawable(view.context, R.drawable.ic_filter))
      binding.textLeague.text = listLeagues[0].strLeague
    })

  }

  private fun showOrHideFilter(drawable: Drawable?) {
    binding.textLeague.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)
  }
}
