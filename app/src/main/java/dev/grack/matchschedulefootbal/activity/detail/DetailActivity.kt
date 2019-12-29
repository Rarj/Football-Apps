package dev.grack.matchschedulefootbal.activity.detail

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.rw.loadingdialog.LoadingView
import com.squareup.picasso.Picasso
import dev.grack.matchschedulefootbal.R
import dev.grack.matchschedulefootbal.R.drawable.ic_love
import dev.grack.matchschedulefootbal.R.drawable.ic_loved
import dev.grack.matchschedulefootbal.R.id.add_to_favorite
import dev.grack.matchschedulefootbal.R.menu.detail_menu
import dev.grack.matchschedulefootbal.db.Favorite
import dev.grack.matchschedulefootbal.db.database
import dev.grack.matchschedulefootbal.model.Event
import dev.grack.matchschedulefootbal.model.Team
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.act
import org.jetbrains.anko.ctx
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity : AppCompatActivity(), AnkoLogger, DetailView {
    private lateinit var presenter: DetailPresenter
    private var idHomeTeam: String? = null
    private var idAwayTeam: String? = null
    private lateinit var loadingView: LoadingView
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private var id: String? = null

    companion object {
        const val POSITIONEXTRA = "position_extra"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val bundle = intent.getBundleExtra(POSITIONEXTRA)
        id = bundle.getString("id")
        idHomeTeam = bundle.getString("idhome")
        idAwayTeam = bundle.getString("idaway")

        presenter = DetailPresenter(this)
        presenter.getDetailList(id)
        presenter.getImageHome(idHomeTeam!!)
        presenter.getImageAway(idAwayTeam!!)

        favoriteState()

        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    @SuppressLint("SimpleDateFormat")
    override fun showDetailTeam(data: List<Event>) {
        for (item: Event in data.iterator()) {
            eventMatch.text = item.strEvent
            teamHome.text = item.strHomeTeam
            teamAway.text = item.strAwayTeam
            dateEvent.text = item.dateEvent

            try {
                val displayFormat = SimpleDateFormat("HH:mm")
                val parseFormat = SimpleDateFormat("HH:mm:ssZ")
                val date: Date = parseFormat.parse(item.strTime.toString())

                time_detail_match.text = displayFormat.format(date)
            } catch (e: Exception) {

            }

            if (item.intHomeScore.equals(null)) scoreHome.text = "0"
            else scoreHome.text = item.intHomeScore
            if (item.intAwayScore.equals(null)) scoreAway.text = "0"
            else scoreAway.text = item.intAwayScore
            goal_home.text = item.strHomeGoalDetails?.split(";").toString()
                    .replace("[", "").trim()
                    .replace("]", "").trim()
                    .replace(", ", ";\n").trim()
                    .replace(",", ";").trim()
            if (item.strHomeGoalDetails.equals(null) || item.strHomeGoalDetails.equals("")) goal_home.text = "-"
            goal_keeper_home.text = item.strHomeLineupGoalkeeper?.split(";").toString()
                    .replace("[", "").trim()
                    .replace("]", "").trim()
                    .replace(", ", ";\n").trim()
                    .replace(",", ";").trim()
            if (item.strHomeLineupGoalkeeper.equals(null) || item.strHomeLineupGoalkeeper.equals("")) goal_keeper_home.text = "-"
            defend_home.text = item.strHomeLineupDefense?.split(";").toString().trim()
                    .replace("[", "").trim()
                    .replace("]", "").trim()
                    .replace(", ", ";\n").trim()
                    .replace(",", ";").trim()
            if (item.strHomeLineupDefense.equals(null) || item.strHomeLineupDefense.equals("")) defend_home.text = "-"
            midfield_home.text = item.strHomeLineupMidfield?.split(";").toString()
                    .replace("[", "").trim()
                    .replace("]", "").trim()
                    .replace(", ", ";\n").trim()
                    .replace(",", ";").trim()
            if (item.strHomeLineupMidfield.equals(null) || item.strHomeLineupMidfield.equals("")) midfield_home.text = "-"
            forward_home.text = item.strHomeLineupForward?.split(";").toString()
                    .replace("[", "").trim()
                    .replace("]", "").trim()
                    .replace(", ", ";\n").trim()
                    .replace(",", ";").trim()
            if (item.strHomeLineupForward.equals(null) || item.strHomeLineupForward.equals("")) forward_home.text = "-"
            subs_home.text = item.strHomeLineupSubstitutes?.split(";").toString()
                    .replace("[", "").trim()
                    .replace("]", "").trim()
                    .replace(", ", ";\n").trim()
                    .replace(",", ";").trim()
            if (item.strHomeLineupSubstitutes.equals(null) || item.strHomeLineupSubstitutes.equals("")) subs_home.text = "-"

            goal_away.text = item.strAwayGoalDetails?.split(";").toString()
                    .replace("[", "").trim()
                    .replace("]", "").trim()
                    .replace(", ", ";\n").trim()
                    .replace(",", ";").trim()
            if (item.strAwayGoalDetails.equals(null) || item.strAwayGoalDetails.equals("")) goal_away.text = "-"
            goal_keeper_away.text = item.strAwayLineupGoalkeeper?.split(";").toString()
                    .replace("[", "").trim()
                    .replace("]", "").trim()
                    .replace(", ", ";\n").trim()
                    .replace(",", ";").trim()
            if (item.strAwayLineupGoalkeeper.equals(null) || item.strAwayLineupGoalkeeper.equals("")) goal_keeper_away.text = "-"
            defend_away.text = item.strAwayLineupDefense?.split(";").toString()
                    .replace("[", "").trim()
                    .replace("]", "").trim()
                    .replace(", ", ";\n").trim()
                    .replace(",", ";idEvent").trim()
            if (item.strAwayLineupDefense.equals(null) || item.strAwayLineupDefense.equals("")) defend_away.text = "-"
            midfield_away.text = item.strAwayLineupMidfield?.split(";").toString()
                    .replace("[", "").trim()
                    .replace("]", "").trim()
                    .replace(", ", ";\n").trim()
                    .replace(",", ";").trim()
            if (item.strAwayLineupMidfield.equals(null) || item.strAwayLineupMidfield.equals("")) midfield_away.text = "-"
            forward_away.text = item.strAwayLineupForward?.split(";").toString()
                    .replace("[", "").trim()
                    .replace("]", "").trim()
                    .replace(", ", ";\n").trim()
                    .replace(",", ";").trim()
            if (item.strAwayLineupForward.equals(null) || item.strAwayLineupForward.equals("")) forward_away.text = "-"
            subs_away.text = item.strAwayLineupSubstitutes?.split(";").toString()
                    .replace("[", "").trim()
                    .replace("]", "").trim()
                    .replace(", ", ";\n").trim()
                    .replace(",", ";").trim()
            if (item.strAwayLineupSubstitutes.equals(null) || item.strAwayLineupSubstitutes.equals("")) subs_away.text = "-"
        }
    }

    private fun favoriteState() {
        database.use {
            val result = select(Favorite.TABLE_FAVORITE).whereArgs("(ID_EVENT = {id})", "id" to id.toString())

            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    private fun setFavorite() {
        if (isFavorite) {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_loved)
        } else {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_love)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            add_to_favorite -> {
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
                delete(Favorite.TABLE_FAVORITE, "(ID_EVENT = {id})", "id" to id.toString())
            }
            Toasty.error(ctx, getString(R.string.date_deleted)).show()
        } catch (e: SQLiteConstraintException) {
            Toasty.success(ctx, e.localizedMessage).show()
        }
    }

    private fun addFavorite() {
        try {
            database.use {
                insert(Favorite.TABLE_FAVORITE,
                        Favorite.ID_EVENT to id,
                        Favorite.TEAM_ID_HOME to idHomeTeam,
                        Favorite.TEAM_ID_AWAY to idAwayTeam,
                        Favorite.TEAM_NAME_HOME to teamHome.text,
                        Favorite.TEAM_NAME_AWAY to teamAway.text,
                        Favorite.TIME_MATCH to time_detail_match.text,
                        Favorite.TEAM_SCORE_HOME to scoreHome.text,
                        Favorite.TEAM_SCORE_AWAY to scoreAway.text,
                        Favorite.TEAM_DATE_EVENT to dateEvent.text)
            }

            Toasty.success(ctx, getString(R.string.added_to_favorite)).show()
        } catch (e: SQLiteConstraintException) {
            Toasty.success(ctx, e.localizedMessage).show()
        }
    }

    override fun showLoading() {
        loadingView = LoadingView.Builder(act)
                .setProgressColorResource(R.color.colorAccent)
                .setProgressStyle(LoadingView.ProgressStyle.CYCLIC)
                .attachTo(act)
        loadingView.show()
    }

    override fun hideLoading() {
        loadingView.hide()
    }

    override fun showImageAwayTeam(data: List<Team>) {
        for (item: Team? in data.iterator()) {
            Picasso.get().load(item?.strTeamBadge).into(badgeAway)
        }
    }

    override fun showImageHomeTeam(data: List<Team>) {
        for (item: Team? in data.iterator()) {
            Picasso.get().load(item?.strTeamBadge).into(badgeHome)
        }
    }
}
