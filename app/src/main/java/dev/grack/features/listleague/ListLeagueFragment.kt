package dev.grack.features.listleague


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dev.grack.repository.leaguelist.model.League
import dev.grack.zmatchschedulefootbal.R
import dev.grack.zmatchschedulefootbal.databinding.FragmentListLeagueBinding

class ListLeagueFragment(private var listLeague: List<League>?) : BottomSheetDialogFragment() {

  lateinit var binding: FragmentListLeagueBinding
  lateinit var adapterListLeague: ListLeagueAdapter

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {

    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_league, container, false)
    binding.lifecycleOwner = this

    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    if (!listLeague.isNullOrEmpty()) {
      adapterListLeague = ListLeagueAdapter(listLeague)
      binding.recyclerLeague.apply {
        layoutManager = LinearLayoutManager(view.context)
        adapter = adapterListLeague
        adapterListLeague.notifyDataSetChanged()
      }
    }

    binding.buttonClose.setOnClickListener { dismiss() }

  }

}