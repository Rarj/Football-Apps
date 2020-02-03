package dev.grack.features.pastmatch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import dev.grack.repository.pastmatch.MatchModel
import dev.grack.zmatchschedulefootbal.BR
import dev.grack.zmatchschedulefootbal.databinding.ItemPastMatchBinding

class PastMatchAdapter(private val listLeague: MutableList<MatchModel>) : RecyclerView.Adapter<PastMatchAdapter.ViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    val viewDataBinding = ItemPastMatchBinding.inflate(inflater, parent, false)
    return ViewHolder(viewDataBinding)
  }

  override fun getItemCount(): Int {
    return listLeague.size
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(listLeague[position])
  }

  class ViewHolder(private val viewDataBinding: ViewDataBinding) : RecyclerView.ViewHolder(viewDataBinding.root) {

    fun bind(itemLeague: MatchModel?) {
      viewDataBinding.setVariable(BR.modelPastMatch, itemLeague)
      viewDataBinding.executePendingBindings()
    }

  }
}