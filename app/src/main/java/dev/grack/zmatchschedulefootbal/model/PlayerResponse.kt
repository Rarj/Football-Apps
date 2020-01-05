package dev.grack.zmatchschedulefootbal.model

import com.google.gson.annotations.SerializedName

data class PlayerResponse(
        @SerializedName("player") var player: List<Player>
)