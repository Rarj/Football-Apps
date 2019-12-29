package dev.grack.matchschedulefootbal.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import dev.grack.matchschedulefootbal.R
import dev.grack.matchschedulefootbal.db.Favorite
import org.jetbrains.anko.find

class FavoriteAdapter(private val favorite: MutableList<Favorite>,
                      private val listener: (Favorite) -> Unit) : androidx.recyclerview.widget.RecyclerView.Adapter<FavoriteViewHolder>() {
    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {

        holder.bindItem(favorite[position], listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_match, parent, false))
    }

    override fun getItemCount() = favorite.size
}

class FavoriteViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
    private val teamHome: TextView = view.find(R.id.team_home)
    private val teamAway: TextView = view.find(R.id.team_away)
    private val date: TextView = view.find(R.id.date_event)
    private val homeScore: TextView = view.findViewById(R.id.home_score)
    private val awayScore: TextView = view.findViewById(R.id.away_score)
    private val timeMatch: TextView = view.findViewById(R.id.time_match)
    private lateinit var idHome: String
    private lateinit var idAway: String
    private lateinit var idEvent: String

    @SuppressLint("SimpleDateFormat")
    fun bindItem(favorite: Favorite, listener: (Favorite) -> Unit) {

        teamHome.text = favorite.strHomeTeam
        teamAway.text = favorite.strAwayTeam
        date.text = favorite.dateEvent
        idHome = favorite.idHomeTeam.toString()
        idAway = favorite.idAwayTeam.toString()
        idEvent = favorite.idEvent.toString()
        timeMatch.text = favorite.strTime.toString()

        if (favorite.intHomeScore.equals(null)) homeScore.text = "0"
        else homeScore.text = favorite.intHomeScore
        if (favorite.intAwayScore.equals(null)) awayScore.text = "0"
        else awayScore.text = favorite.intAwayScore

        itemView.setOnClickListener {
            listener(favorite)
        }
    }
}
