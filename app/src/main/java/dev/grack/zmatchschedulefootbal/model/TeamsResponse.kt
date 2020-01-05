package dev.grack.zmatchschedulefootbal.model

import com.google.gson.annotations.SerializedName

data class TeamsResponse(
        @SerializedName("teams") var teams: List<Team>
)