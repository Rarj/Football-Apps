package dev.grack.features.pastmatch

import android.annotation.SuppressLint
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
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding3.view.clicks
import dagger.android.support.AndroidSupportInjection
import dev.grack.Constant.DURATION
import dev.grack.features.listleague.ListLeagueBottomSheet
import dev.grack.listener.ListenerBottomSheetDialog
import dev.grack.repository.leaguelist.model.League
import dev.grack.zmatchschedulefootbal.R
import dev.grack.zmatchschedulefootbal.databinding.PastMatchFragmentBinding
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class PastMatchFragment : Fragment() {

  @Inject
  lateinit var viewModeFactory: ViewModelProvider.Factory

  private lateinit var viewModel: PastMatchViewModel
  private lateinit var binding: PastMatchFragmentBinding

  private lateinit var adapterPastMatch: PastMatchAdapter

  override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
  ): View? {

    AndroidSupportInjection.inject(this)

    viewModel = ViewModelProvider(this, viewModeFactory).get(PastMatchViewModel::class.java)
    binding = DataBindingUtil.inflate(
          inflater,
          R.layout.past_match_fragment,
          container,
          false)
    binding.viewModel = viewModel
    binding.lifecycleOwner = this

    showOrHideFilter(null)

    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    viewModel.loadSoccerLeague()
    viewModel.loadPastMatch()

    observer()
    listener()
  }

  private fun observer() {
    viewModel.selectedIdLeague.observe(viewLifecycleOwner, Observer { id ->
      viewModel.loadPastMatch(id)
    })

    viewModel.listLeagues.observe(viewLifecycleOwner, Observer { listLeagues ->
      binding.progressCircular.visibility = GONE
      showOrHideFilter(ContextCompat.getDrawable(activity?.applicationContext!!, R.drawable.ic_filter))
      binding.textLeague.text = listLeagues[0].strLeague
    })

    viewModel.listPastMatch.observe(viewLifecycleOwner, Observer {
      adapterPastMatch = PastMatchAdapter(viewModel.matchModel)
      binding.recyclerPastMatch.apply {
        layoutManager = LinearLayoutManager(activity?.applicationContext)
        adapter = adapterPastMatch
        adapterPastMatch.notifyDataSetChanged()
        viewModel.isLoading.value = false
      }
    })
  }

  @SuppressLint("CheckResult")
  private fun listener() {
    binding.textLeague.clicks()
          .throttleFirst(DURATION, TimeUnit.MILLISECONDS)
          .subscribe {
            val listLeague = ListLeagueBottomSheet(
                  viewModel.listLeagues.value,
                  object : ListenerBottomSheetDialog<League> {
                    override fun onItemClickListener(item: League?) {
                      viewModel.isLoading.value = true
                      viewModel.selectedLeague.value = item?.strLeague.toString()
                      viewModel.selectedIdLeague.value = item?.idLeague.toString()
                    }
                  })
            listLeague.show(activity?.supportFragmentManager!!, "list_league")
          }
  }

  private fun showOrHideFilter(drawable: Drawable?) {
    binding.textLeague.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)
  }
}