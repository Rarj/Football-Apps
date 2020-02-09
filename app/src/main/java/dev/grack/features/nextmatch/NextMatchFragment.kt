package dev.grack.features.nextmatch


import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding3.view.clicks
import dagger.android.support.AndroidSupportInjection
import dev.grack.Constant
import dev.grack.features.listleague.ListLeagueBottomSheet
import dev.grack.listener.ListenerBottomSheetDialog
import dev.grack.repository.leaguelist.model.League
import dev.grack.zmatchschedulefootbal.R
import dev.grack.zmatchschedulefootbal.databinding.FragmentNextMatch2Binding
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class NextMatchFragment : Fragment() {

  @Inject
  lateinit var viewModeFactory: ViewModelProvider.Factory

  lateinit var viewModel: NextMatchViewModel
  lateinit var binding: FragmentNextMatch2Binding

  private lateinit var adapterNextMatch: NextMatchAdapter

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {

    AndroidSupportInjection.inject(this)

    viewModel = ViewModelProvider(this, viewModeFactory).get(NextMatchViewModel::class.java)
    binding = DataBindingUtil.inflate(
          inflater,
          R.layout.fragment_next_match2,
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
    viewModel.loadNextMatch()

    observer()
    listener()
  }

  private fun observer() {
    viewModel.selectedIdLeague.observe(viewLifecycleOwner, Observer { id ->
      viewModel.loadNextMatch(id)
    })

    viewModel.listLeagues.observe(viewLifecycleOwner, Observer { listLeagues ->
      binding.progressCircular.visibility = View.GONE
      showOrHideFilter(ContextCompat.getDrawable(activity?.applicationContext!!, R.drawable.ic_filter))
      binding.textLeague.text = listLeagues[0].strLeague
    })

    viewModel.listPastMatch.observe(viewLifecycleOwner, Observer {
      adapterNextMatch = NextMatchAdapter(viewModel.matchModel)
      binding.recyclerPastMatch.apply {
        layoutManager = LinearLayoutManager(activity?.applicationContext)
        adapter = adapterNextMatch
        adapterNextMatch.notifyDataSetChanged()
        viewModel.isLoading.value = false
      }
    })
  }

  @SuppressLint("CheckResult")
  private fun listener() {
    binding.textLeague.clicks()
          .throttleFirst(Constant.DURATION, TimeUnit.MILLISECONDS)
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
