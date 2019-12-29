package dev.grack.matchschedulefootbal.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import dev.grack.matchschedulefootbal.R
import dev.grack.matchschedulefootbal.model.Player
import org.jetbrains.anko.find

class PlayerAdapter(private var nextmatchs: MutableList<Player>,
                    private var listener: (Player) -> Unit) :
        androidx.recyclerview.widget.RecyclerView.Adapter<PlayerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_player, parent, false))
    }

    override fun getItemCount() = nextmatchs.size

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bindItem(nextmatchs[position], listener)
    }
}

class PlayerViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
    private val teamHome: TextView = view.find(R.id.player_name)
    private val imageTeam: ImageView = view.find(R.id.player_image)
    private val position: TextView = view.find(R.id.position)

    fun bindItem(nextMatch: Player, listener: (Player) -> Unit) {
        teamHome.text = nextMatch.strPlayer
        Picasso.get().load(nextMatch.strCutout).into(imageTeam)
        position.text = nextMatch.strPosition

        itemView.setOnClickListener {
            listener(nextMatch)
        }
    }
}