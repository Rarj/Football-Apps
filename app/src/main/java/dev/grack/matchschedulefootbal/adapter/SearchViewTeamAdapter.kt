package dev.grack.matchschedulefootbal.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import dev.grack.matchschedulefootbal.R
import dev.grack.matchschedulefootbal.model.TeamSearch
import org.jetbrains.anko.find

class SearchViewTeamAdapter(private var nextmatchs: MutableList<TeamSearch>,
                            private var listener: (TeamSearch) -> Unit) :
        androidx.recyclerview.widget.RecyclerView.Adapter<SearchViewTeamHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewTeamHolder {
        return SearchViewTeamHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_search_team, parent, false))
    }

    override fun getItemCount() = nextmatchs.size

    override fun onBindViewHolder(holder: SearchViewTeamHolder, position: Int) {
        holder.bindItem(nextmatchs[position], listener)
    }
}

class SearchViewTeamHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
    private val teamHome: TextView = view.find(R.id.text_team)
    private val imageTeam: ImageView = view.find(R.id.image_team)

    fun bindItem(nextMatch: TeamSearch, listener: (TeamSearch) -> Unit) {
        teamHome.text = nextMatch.strTeam
        Picasso.get().load(nextMatch.strTeamBadge).into(imageTeam)

        itemView.setOnClickListener {
            listener(nextMatch)
        }
    }
}