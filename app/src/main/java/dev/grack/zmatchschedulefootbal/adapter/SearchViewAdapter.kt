package dev.grack.zmatchschedulefootbal.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import dev.grack.zmatchschedulefootbal.R
import dev.grack.zmatchschedulefootbal.model.EventSearch
import org.jetbrains.anko.find
import java.text.SimpleDateFormat
import java.util.*

class SearchViewAdapter(private var nextmatchs: MutableList<EventSearch>,
                        private var listener: (EventSearch) -> Unit) :
        androidx.recyclerview.widget.RecyclerView.Adapter<SearchViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false))
    }

    override fun getItemCount() = nextmatchs.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bindItem(nextmatchs[position], listener)
    }
}

class SearchViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
    private val teamHome: TextView = view.find(R.id.team_home)
    private val teamAway: TextView = view.find(R.id.team_away)
    private val date: TextView = view.find(R.id.date_event)
    private val homeScore: TextView = view.find(R.id.home_score)
    private val awayScore: TextView = view.find(R.id.away_score)
    private val timeSearchTV: TextView = view.findViewById(R.id.time_search)
    private lateinit var idHome: String
    private lateinit var idAway: String
    private lateinit var matchEvent: String
    private lateinit var timeSearch: String

    @SuppressLint("SimpleDateFormat")
    fun bindItem(nextMatch: EventSearch, listener: (EventSearch) -> Unit) {
        teamHome.text = nextMatch.strHomeTeam
        teamAway.text = nextMatch.strAwayTeam
        date.text = nextMatch.dateEvent
        idHome = nextMatch.idHomeTeam.toString()
        idAway = nextMatch.idAwayTeam.toString()
        matchEvent = nextMatch.strEvent.toString()
        timeSearch = nextMatch.strTime.toString()

        try {
            val displayFormat = SimpleDateFormat("HH:mm")
            val parseFormat = SimpleDateFormat("HH:mm:ssZ")
            val date: Date = parseFormat.parse(nextMatch.strTime.toString())

            if (!nextMatch.strTime.equals(null)) timeSearchTV.text = displayFormat.format(date)
            else timeSearchTV.text = "--:--"
        } catch (e: Exception) {
            e.stackTrace
        }

        if (nextMatch.intHomeScore.equals(null)) homeScore.text = "0"
        else homeScore.text = nextMatch.intHomeScore
        if (nextMatch.intAwayScore.equals(null)) awayScore.text = "0"
        else awayScore.text = nextMatch.intAwayScore

        itemView.setOnClickListener {
            listener(nextMatch)
        }
    }
}
