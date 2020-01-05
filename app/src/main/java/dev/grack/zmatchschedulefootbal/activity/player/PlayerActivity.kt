package dev.grack.zmatchschedulefootbal.activity.player

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rw.loadingdialog.LoadingView
import dev.grack.zmatchschedulefootbal.R
import dev.grack.zmatchschedulefootbal.activity.playerdetail.PlayerDetailActivity
import dev.grack.zmatchschedulefootbal.adapter.PlayerAdapter
import dev.grack.zmatchschedulefootbal.model.Player
import kotlinx.android.synthetic.main.activity_player.*
import org.jetbrains.anko.act
import org.jetbrains.anko.ctx
import org.jetbrains.anko.find

class PlayerActivity : AppCompatActivity(), PlayerView {
    private lateinit var presenter: PlayerPresenter
    private lateinit var mAdapter: PlayerAdapter
    private val events: MutableList<Player> = mutableListOf()
    private lateinit var recy: androidx.recyclerview.widget.RecyclerView
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
        recy.layoutManager = androidx.recyclerview.widget.GridLayoutManager(ctx, 2)

        presenter = PlayerPresenter(this)
        presenter.getPlayer(idTeam)
    }

    override fun showLoading() {
        loadingView = LoadingView.Builder(act)
                .setProgressColorResource(R.color.night_shadz_b83b5e)
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
