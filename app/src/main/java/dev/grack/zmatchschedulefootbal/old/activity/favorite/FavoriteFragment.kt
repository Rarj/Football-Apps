package dev.grack.zmatchschedulefootbal.old.activity.favorite

import androidx.fragment.app.Fragment
import dev.grack.zmatchschedulefootbal.db.Favorite


class FavoriteFragment : Fragment(), FavoriteView {


  override fun showFavoriteList(data: List<Favorite>) {

  }

  //  private var favorites: MutableList<Favorite> = mutableListOf()
//  private lateinit var mAdapter: FavoriteAdapter
//  private lateinit var listEvent: androidx.recyclerview.widget.RecyclerView
//  private lateinit var swipeRefresh: androidx.swiperefreshlayout.widget.SwipeRefreshLayout
//  private lateinit var presenter: FavoritePresenter
//
//  override fun onActivityCreated(savedInstanceState: Bundle?) {
//    super.onActivityCreated(savedInstanceState)
//
//    presenter = FavoritePresenter(this, ctx)
//
//    mAdapter = FavoriteAdapter(favorites) {
//      val intent = Intent(ctx, DetailActivity::class.java)
//      val bundle = Bundle()
//      bundle.putString("id", it.idEvent)
//      bundle.putString("idhome", it.idHomeTeam)
//      bundle.putString("idaway", it.idAwayTeam)
//      intent.putExtra(DetailActivity.POSITIONEXTRA, bundle)
//      startActivity(intent)
//    }
//
//    swipeRefresh.isRefreshing = true
//    listEvent.adapter = mAdapter
//    presenter.getFavorite()
//  }
//
//  override fun onResume() {
//    super.onResume()
//    presenter.getFavorite()
//  }
//
//  @SuppressLint("ResourceType")
//  override fun createView(ui: AnkoContext<Context>): View = with(ui) {
//    linearLayout {
//      lparams(width = matchParent, height = wrapContent)
//      topPadding = dip(16)
//      leftPadding = dip(16)
//      rightPadding = dip(16)
//
//      swipeRefresh = swipeRefreshLayout {
//        onRefresh {
//          presenter.getFavorite()
//        }
//        setColorSchemeResources(android.R.color.holo_blue_bright,
//              android.R.color.holo_green_light,
//              android.R.color.holo_orange_light,
//              android.R.color.holo_red_light)
//
//        listEvent = recyclerView {
//          id = R.id.list_favorite
//          lparams(width = matchParent, height = wrapContent)
//          layoutManager = androidx.recyclerview.widget.LinearLayoutManager(ctx)
//        }
//      }
//    }
//  }
//
//  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
//                            savedInstanceState: Bundle?): View? {
//    return createView(AnkoContext.create(ctx))
//  }
//
//  override fun showFavoriteList(data: List<Favorite>) {
//    favorites.clear()
//    favorites.addAll(data)
//    mAdapter.notifyDataSetChanged()
//    swipeRefresh.isRefreshing = false
//  }
}
