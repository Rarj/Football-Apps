package dev.grack.zmatchschedulefootbal.activity.pastmatch

import androidx.fragment.app.Fragment

class PastMatchFragment : Fragment() {

  //    private lateinit var dataLeague: ArrayList<String>
//    private lateinit var idLiga: ArrayList<String>
//    private lateinit var mAdapter: NextMatchAdapter
//    private val events: MutableList<Event> = mutableListOf()
//    private lateinit var presenter: PastMatchPresenter
//    private lateinit var spinner: MaterialSpinner
//    private lateinit var a: androidx.recyclerview.widget.RecyclerView
//    private var idStart: String = "4328"
//    private lateinit var loadingView: LoadingView
//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//
//        presenter = PastMatchPresenter(this@pastmatch)
//        dataLeague = ArrayList()
//        idLiga = ArrayList()
//
//        spinner = this.find(R.id.spinnerPast)
//        a = this.find(R.id.recycler_past)
//
//        mAdapter = NextMatchAdapter(events) {
//            val intent = Intent(activity, DetailActivity::class.java)
//            val bundle = Bundle()
//            bundle.putString("id", it.idEvent)
//            bundle.putString("idhome", it.idHomeTeam)
//            bundle.putString("idaway", it.idAwayTeam)
//            intent.putExtra(DetailActivity.POSITIONEXTRA, bundle)
//            startActivity(intent)
//        }
//
//        a.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
//        a.adapter = mAdapter
//
//        presenter.getTeamList(idStart)
//        presenter.getAllLeagues()
//
//        selectSpinner()
//
//    }
//
//    private fun selectSpinner() {
//        spinner.setOnItemSelectedListener { _, position, _, _ ->
//            presenter.getTeamList(idLiga[position])
//        }
//    }
//
//    @SuppressLint("ResourceAsColor")
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
//                              savedInstanceState: Bundle?): View {
//
//        return inflater.inflate(R.layout.fragment_past_match, container, false)
//    }
//
//    override fun showLoading() {
//        loadingView = LoadingView.Builder(activity)
//                .setProgressColorResource(R.color.night_shadz_b83b5e)
//                .setProgressStyle(LoadingView.ProgressStyle.CYCLIC)
//                .attachTo(activity)
//        loadingView.show()
//    }
//
//    override fun hideLoading() {
//        loadingView.hide()
//    }
//
//    override fun showTeamList(data: List<Event>) {
//        events.clear()
//        events.addAll(data)
//        mAdapter.notifyDataSetChanged()
//    }
//
//    override fun showLeagueList(data: List<League>) {
//        for (items in data.iterator()) {
//            dataLeague.add(items.strLeague.toString())
//            idLiga.add(items.idLeague.toString())
//
//            spinner.setItems(dataLeague)
//        }
//    }

}
