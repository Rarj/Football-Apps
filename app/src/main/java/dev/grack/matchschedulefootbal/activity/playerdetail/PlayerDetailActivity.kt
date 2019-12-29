package dev.grack.matchschedulefootbal.activity.playerdetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import dev.grack.matchschedulefootbal.R
import dev.grack.matchschedulefootbal.model.Player
import kotlinx.android.synthetic.main.activity_player_detail.*
import kotlinx.android.synthetic.main.content_player_detail.*

class PlayerDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)
        setSupportActionBar(toolbar)

        val bundle = intent.getBundleExtra("bundleTeam")
        val teams = bundle!!.getParcelable<Player>("detailTeam")

        Picasso.get().load(teams?.strFanart1).into(image_player_detail)
        toolbar.title = teams?.strPlayer
        setSupportActionBar(toolbar)
        height.text = teams?.strHeight
        weight.text = teams?.strWeight
        position_detail.text = teams?.strPosition
        deskripsi.text = teams?.strDescriptionEN
    }
}
