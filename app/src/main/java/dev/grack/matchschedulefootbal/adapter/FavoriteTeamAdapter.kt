package dev.grack.matchschedulefootbal.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import dev.grack.matchschedulefootbal.R
import dev.grack.matchschedulefootbal.db.FavoriteTeam
import org.jetbrains.anko.find

class FavoriteTeamAdapter(private val favorite: MutableList<FavoriteTeam>,
                          private val listener: (FavoriteTeam) -> Unit) : androidx.recyclerview.widget.RecyclerView.Adapter<FavoriteTeamViewHolder>() {

    override fun onBindViewHolder(holder: FavoriteTeamViewHolder, position: Int) {
        holder.bindItem(favorite[position], listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteTeamViewHolder {
        return FavoriteTeamViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_search_team, parent, false))
    }

    override fun getItemCount() = favorite.size
}

class FavoriteTeamViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
    private val imageTeam: ImageView = view.find(R.id.image_team)
    private val textTeam: TextView = view.find(R.id.text_team)

    fun bindItem(favorite: FavoriteTeam, listener: (FavoriteTeam) -> Unit) {
        textTeam.text = favorite.strTeam
        Picasso.get().load(favorite.strTeamBadge).into(imageTeam)

        itemView.setOnClickListener {
            listener(favorite)
        }
    }
}
