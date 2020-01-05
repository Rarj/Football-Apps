package dev.grack.zmatchschedulefootbal.activity.nextmatch


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jaredrummler.materialspinner.MaterialSpinner
import com.rw.loadingdialog.LoadingView
import dev.grack.zmatchschedulefootbal.R
import dev.grack.zmatchschedulefootbal.activity.detail.DetailActivity
import dev.grack.zmatchschedulefootbal.adapter.NextMatchAdapter
import dev.grack.zmatchschedulefootbal.model.Event
import dev.grack.zmatchschedulefootbal.model.League
import org.jetbrains.anko.AnkoLogger
import java.util.*


class NextMatchFragment : Fragment() {

//    private lateinit var dataLeague: ArrayList<String>
//    private lateinit var idLiga: ArrayList<String>
//    private lateinit var mAdapter: NextMatchAdapter
//    private val events: MutableList<Event> = mutableListOf()
//    private lateinit var preseneter: NextMatchPreseneter
//    private lateinit var spinner: MaterialSpinner
//    private lateinit var loadingView: LoadingView
//
//    private lateinit var a: androidx.recyclerview.widget.RecyclerView
//    private var idStart: String = "4328"
//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//
//        preseneter = NextMatchPreseneter(this@NextMatchFragment)
//        dataLeague = ArrayList()
//        idLiga = ArrayList()
//
//        spinner = this.find(R.id.spinnerNext)
//        a = this.find(R.id.recycler_next)
//
//        mAdapter = NextMatchAdapter(events) {
//            val intent = Intent(ctx, DetailActivity::class.java)
//            val bundle = Bundle()
//            bundle.putString("id", it.idEvent)
//            bundle.putString("idhome", it.idHomeTeam)
//            bundle.putString("idaway", it.idAwayTeam)
//            intent.putExtra(DetailActivity.POSITIONEXTRA, bundle)
//            startActivity(intent)
//        }
//
//        a.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(ctx)
//        a.adapter = mAdapter
//
//        preseneter.getTeamList(idStart)
//        preseneter.getAllLeagues()
//
//        selectSpinner()
//    }
//
//    private fun selectSpinner() {
//        spinner.setOnItemSelectedListener { _, position, _, _ ->
//            preseneter.getTeamList(idLiga[position])
//        }
//    }
//
//    @SuppressLint("PrivateResource", "ResourceAsColor")
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
//                              savedInstanceState: Bundle?): View {
//
//        return inflater.inflate(R.layout.fragment_next_match, container, false)
//
//    }
//
//    override fun showLoading() {
//        loadingView = LoadingView.Builder(act)
//                .setProgressColorResource(R.color.night_shadz_b83b5e)
//                .setProgressStyle(LoadingView.ProgressStyle.CYCLIC)
//                .attachTo(act)
//
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