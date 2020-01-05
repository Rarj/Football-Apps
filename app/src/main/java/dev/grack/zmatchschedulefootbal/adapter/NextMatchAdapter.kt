package dev.grack.zmatchschedulefootbal.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import dev.grack.zmatchschedulefootbal.R
import dev.grack.zmatchschedulefootbal.model.Event
import org.jetbrains.anko.find
import java.text.SimpleDateFormat
import java.util.*

class NextMatchAdapter(private var nextmatchs: MutableList<Event>,
                       private var listener: (Event) -> Unit) :
        androidx.recyclerview.widget.RecyclerView.Adapter<NextMatchViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NextMatchViewHolder {
        return NextMatchViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_match, parent, false))
    }

    override fun getItemCount() = nextmatchs.size

    override fun onBindViewHolder(holder: NextMatchViewHolder, position: Int) {
        holder.bindItem(nextmatchs[position], listener)
    }
}

class NextMatchViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
    private val teamHome: TextView = view.find(R.id.team_home)
    private val teamAway: TextView = view.find(R.id.team_away)
    private val date: TextView = view.find(R.id.date_event)
    private val homeScore: TextView = view.findViewById(R.id.home_score)
    private val awayScore: TextView = view.findViewById(R.id.away_score)
    private val timeMatch: TextView = view.findViewById(R.id.time_match)
    private lateinit var idHome: String
    private lateinit var idAway: String
    private lateinit var matchEvent: String

    @SuppressLint("SimpleDateFormat")
    fun bindItem(nextMatch: Event, listener: (Event) -> Unit) {
        teamHome.text = nextMatch.strHomeTeam
        teamAway.text = nextMatch.strAwayTeam
        date.text = nextMatch.dateEvent
        idHome = nextMatch.idHomeTeam.toString()
        idAway = nextMatch.idAwayTeam.toString()
        matchEvent = nextMatch.strEvent.toString()

        val displayFormat = SimpleDateFormat("HH:mm")
        val parseFormat = SimpleDateFormat("HH:mm:ssZ")
        val date: Date = parseFormat.parse(nextMatch.strTime.toString())

        timeMatch.text = displayFormat.format(date)

        if (nextMatch.intHomeScore.equals(null)) homeScore.text = "0"
        else homeScore.text = nextMatch.intHomeScore
        if (nextMatch.intAwayScore.equals(null)) awayScore.text = "0"
        else awayScore.text = nextMatch.intAwayScore

        itemView.setOnClickListener {
            listener(nextMatch)
        }
    }
}
