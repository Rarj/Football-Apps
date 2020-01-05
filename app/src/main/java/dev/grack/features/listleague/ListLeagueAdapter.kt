package dev.grack.features.listleague

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import dev.grack.repository.leaguelist.model.League
import dev.grack.zmatchschedulefootbal.BR
import dev.grack.zmatchschedulefootbal.databinding.ItemPastMatchBinding

class ListLeagueAdapter(private val listLeague: List<League>?) :
      RecyclerView.Adapter<ListLeagueAdapter.ViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    val viewDataBinding = ItemPastMatchBinding.inflate(inflater, parent, false)
    return ViewHolder(viewDataBinding)
  }

  override fun getItemCount() = listLeague!!.size

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(listLeague?.get(position))
  }

  class ViewHolder(private val viewDataBinding: ViewDataBinding) : RecyclerView.ViewHolder(viewDataBinding.root) {

    fun bind(itemLeague: League?) {
      viewDataBinding.setVariable(BR.modelLeague, itemLeague)
      viewDataBinding.executePendingBindings()
    }

  }
}