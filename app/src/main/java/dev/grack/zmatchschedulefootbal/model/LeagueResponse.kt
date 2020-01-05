package dev.grack.zmatchschedulefootbal.model

import com.google.gson.annotations.SerializedName

data class LeagueResponse(
        @SerializedName("leagues") var leagues: List<League>
)