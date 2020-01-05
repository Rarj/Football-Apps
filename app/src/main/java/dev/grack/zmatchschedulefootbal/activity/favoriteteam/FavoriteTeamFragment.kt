package dev.grack.zmatchschedulefootbal.activity.favoriteteam

import androidx.fragment.app.Fragment
import dev.grack.zmatchschedulefootbal.db.FavoriteTeam

class FavoriteTeamFragment : Fragment(), FavoriteTeamView {
  override fun showFavoriteTeamList(data: List<FavoriteTeam>) {

  }

  //    private var favorites: MutableList<FavoriteTeam> = mutableListOf()
//    private lateinit var mAdapter: FavoriteTeamAdapter
//    private lateinit var listEvent: androidx.recyclerview.widget.RecyclerView
//    private lateinit var swipeRefresh: androidx.swiperefreshlayout.widget.SwipeRefreshLayout
//    private lateinit var presenter: FavoriteTeamPresenter
//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        presenter = FavoriteTeamPresenter(this, ctx)
//
//        mAdapter = FavoriteTeamAdapter(favorites) {
//            val intent = Intent(ctx, DetailTeamActivity::class.java)
//            val bundle = Bundle()
//            bundle.putString("id", it.idTeam)
//            intent.putExtra(DetailTeamActivity.POSITIONEXTRA, bundle)
//            startActivity(intent)
//        }
//
//        swipeRefresh.isRefreshing = true
//        listEvent.adapter = mAdapter
//        presenter.getFavoriteTeam()
//    }
//
//    override fun onResume() {
//        super.onResume()
//        presenter.getFavoriteTeam()
//    }
//
//    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
//        linearLayout {
//            lparams(width = matchParent, height = wrapContent)
//            topPadding = dip(16)
//            leftPadding = dip(16)
//            rightPadding = dip(16)
//
//            swipeRefresh = swipeRefreshLayout {
//                onRefresh {
//                    presenter.getFavoriteTeam()
//                }
//                setColorSchemeResources(android.R.color.holo_blue_bright,
//                        android.R.color.holo_green_light,
//                        android.R.color.holo_orange_light,
//                        android.R.color.holo_red_light)
//
//                listEvent = recyclerView {
//                    id = R.id.list_favorite_team
//                    lparams(width = matchParent, height = wrapContent)
//                    layoutManager = androidx.recyclerview.widget.LinearLayoutManager(ctx)
//                }
//            }
//        }
//    }
//
//    override fun showFavoriteTeamList(data: List<FavoriteTeam>) {
//        favorites.clear()
//        favorites.addAll(data)
//        mAdapter.notifyDataSetChanged()
//        swipeRefresh.isRefreshing = false
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
//                              savedInstanceState: Bundle?): View? {
//        return createView(AnkoContext.create(ctx))
//    }
}
