package dev.grack.matchschedulefootbal.activity.player

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.rw.loadingdialog.LoadingView
import dev.grack.matchschedulefootbal.R
import dev.grack.matchschedulefootbal.activity.playerdetail.PlayerDetailActivity
import dev.grack.matchschedulefootbal.adapter.PlayerAdapter
import dev.grack.matchschedulefootbal.model.Player
import kotlinx.android.synthetic.main.activity_player.*
import org.jetbrains.anko.act
import org.jetbrains.anko.ctx
import org.jetbrains.anko.find

class PlayerActivity : AppCompatActivity(), PlayerView {
    private lateinit var presenter: PlayerPresenter
    private lateinit var mAdapter: PlayerAdapter
    private val events: MutableList<Player> = mutableListOf()
    private lateinit var recy: RecyclerView
    private lateinit var loadingView: LoadingView
    private lateinit var idTeam: String
    private lateinit var teamName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        idTeam = intent.getStringExtra("idTeam")
        teamName = intent.getStringExtra("teamName")

        setSupportActionBar(toolbar_player)
        toolbar_player.title = teamName

        recy = this.find(R.id.recycler_player)

        mAdapter = PlayerAdapter(events) {
            val intent = Intent(this, PlayerDetailActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable("detailTeam", it)
            intent.putExtra("bundleTeam", bundle)
            startActivity(intent)
        }

        recy.adapter = mAdapter
        recy.layoutManager = GridLayoutManager(ctx, 2)

        presenter = PlayerPresenter(this)
        presenter.getPlayer(idTeam)
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

    override fun showSearchList(data: List<Player>) {
        events.clear()
        events.addAll(data)
        mAdapter.notifyDataSetChanged()
    }

}
