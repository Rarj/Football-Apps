package dev.grack.matchschedulefootbal.activity.favoriteteam

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.grack.matchschedulefootbal.R
import dev.grack.matchschedulefootbal.activity.detailteam.DetailTeamActivity
import dev.grack.matchschedulefootbal.adapter.FavoriteTeamAdapter
import dev.grack.matchschedulefootbal.db.FavoriteTeam
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class FavoriteTeamFragment : Fragment(), AnkoComponent<Context>, FavoriteTeamView {

    private var favorites: MutableList<FavoriteTeam> = mutableListOf()
    private lateinit var mAdapter: FavoriteTeamAdapter
    private lateinit var listEvent: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var presenter: FavoriteTeamPresenter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = FavoriteTeamPresenter(this, ctx)

        mAdapter = FavoriteTeamAdapter(favorites) {
            val intent = Intent(ctx, DetailTeamActivity::class.java)
            val bundle = Bundle()
            bundle.putString("id", it.idTeam)
            intent.putExtra(DetailTeamActivity.POSITIONEXTRA, bundle)
            startActivity(intent)
        }

        swipeRefresh.isRefreshing = true
        listEvent.adapter = mAdapter
        presenter.getFavoriteTeam()
    }

    override fun onResume() {
        super.onResume()
        presenter.getFavoriteTeam()
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            swipeRefresh = swipeRefreshLayout {
                onRefresh {
                    presenter.getFavoriteTeam()
                }
                setColorSchemeResources(android.R.color.holo_blue_bright,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                listEvent = recyclerView {
                    id = R.id.list_favorite_team
                    lparams(width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(ctx)
                }
            }
        }
    }

    override fun showFavoriteTeamList(data: List<FavoriteTeam>) {
        favorites.clear()
        favorites.addAll(data)
        mAdapter.notifyDataSetChanged()
        swipeRefresh.isRefreshing = false
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }
}
