package dev.grack.matchschedulefootbal.activity.favorite


import android.annotation.SuppressLint
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
import dev.grack.matchschedulefootbal.activity.detail.DetailActivity
import dev.grack.matchschedulefootbal.adapter.FavoriteAdapter
import dev.grack.matchschedulefootbal.db.Favorite
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class FavoriteFragment : Fragment(), AnkoComponent<Context>, FavoriteView {
    private var favorites: MutableList<Favorite> = mutableListOf()
    private lateinit var mAdapter: FavoriteAdapter
    private lateinit var listEvent: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var presenter: FavoritePresenter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = FavoritePresenter(this, ctx)

        mAdapter = FavoriteAdapter(favorites) {
            val intent = Intent(ctx, DetailActivity::class.java)
            val bundle = Bundle()
            bundle.putString("id", it.idEvent)
            bundle.putString("idhome", it.idHomeTeam)
            bundle.putString("idaway", it.idAwayTeam)
            intent.putExtra(DetailActivity.POSITIONEXTRA, bundle)
            startActivity(intent)
        }

        swipeRefresh.isRefreshing = true
        listEvent.adapter = mAdapter
        presenter.getFavorite()
    }

    override fun onResume() {
        super.onResume()
        presenter.getFavorite()
    }

    @SuppressLint("ResourceType")
    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            swipeRefresh = swipeRefreshLayout {
                onRefresh {
                    presenter.getFavorite()
                }
                setColorSchemeResources(android.R.color.holo_blue_bright,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                listEvent = recyclerView {
                    id = R.id.list_favorite
                    lparams(width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(ctx)
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    override fun showFavoriteList(data: List<Favorite>) {
        favorites.clear()
        favorites.addAll(data)
        mAdapter.notifyDataSetChanged()
        swipeRefresh.isRefreshing = false
    }
}
