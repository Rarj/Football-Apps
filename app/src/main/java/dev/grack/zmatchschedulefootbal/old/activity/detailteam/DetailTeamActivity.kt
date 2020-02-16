package dev.grack.zmatchschedulefootbal.old.activity.detailteam

import android.content.Intent
import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.squareup.picasso.Picasso
import dev.grack.zmatchschedulefootbal.R
import dev.grack.zmatchschedulefootbal.old.activity.player.PlayerActivity
import dev.grack.zmatchschedulefootbal.db.FavoriteTeam
import dev.grack.zmatchschedulefootbal.db.database
import dev.grack.zmatchschedulefootbal.model.TeamSearch
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_detail_teams.*
import kotlinx.android.synthetic.main.content_detail_team.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.ctx
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class DetailTeamActivity : AppCompatActivity(), AnkoLogger, DetailTeamView {
    private lateinit var presenter: DetailTeamPresenter
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private var id: String? = null
    private var teamBadge: String? = null

    companion object {
        const val POSITIONEXTRA = "position_extra"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_teams)

        val bundle = intent.getBundleExtra(POSITIONEXTRA)
        id = bundle.getString("id")
        teamBadge = bundle.getString("teamBadge")

        presenter = DetailTeamPresenter(this)
        presenter.getDetailTeamList(id)

        detail_player.setOnClickListener {
            val intent = Intent(this, PlayerActivity::class.java)
            intent.putExtra("idTeam", id)
            intent.putExtra("teamName", toolbar_team.title.toString())
            startActivity(intent)
        }

        favoriteState()
    }

    override fun showDetailTeams(data: List<TeamSearch>) {
        for (item: TeamSearch in data.iterator()) {
            toolbar_team.title = item.strTeam
            setSupportActionBar(toolbar_team)
            supportActionBar?.setDisplayShowHomeEnabled(true)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            year.text = item.intFormedYear
            stadium.text = item.strStadium
            deskripsi.text = item.strDescriptionEN
            Picasso.get().load(item.strTeamBanner).into(image_banner)
        }
    }

    private fun favoriteState() {
        database.use {
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM).whereArgs("(ID_TEAM = {id})",
                    "id" to id.toString())

            val favorite = result.parseList(classParser<FavoriteTeam>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    private fun setFavorite() {
        if (isFavorite) {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_loved)
        } else {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_love)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addFavorite()
                isFavorite = !isFavorite
                setFavorite()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(FavoriteTeam.TABLE_FAVORITE_TEAM, "(ID_TEAM = {id})",
                        "id" to id.toString())
            }
            Toasty.error(ctx, getString(R.string.date_deleted)).show()
        } catch (e: SQLiteConstraintException) {
            Toasty.success(ctx, e.localizedMessage).show()
        }
    }

    private fun addFavorite() {
        try {
            database.use {
                insert(FavoriteTeam.TABLE_FAVORITE_TEAM,
                        FavoriteTeam.ID_TEAM to id.toString(),
                        FavoriteTeam.STR_TEAM to toolbar_team.title.toString(),
                        FavoriteTeam.STR_TEAM_BADGE to teamBadge.toString(),
                        FavoriteTeam.STR_FORMED_YEAR to year.text.toString(),
                        FavoriteTeam.STR_STADIUM to stadium.text.toString(),
                        FavoriteTeam.STR_DESCRIPTION_EN to deskripsi.text.toString())
            }
            Toasty.success(ctx, getString(R.string.added_to_favorite)).show()
        } catch (e: SQLiteConstraintException) {
            Toasty.success(ctx, e.localizedMessage).show()
        }
    }

}
