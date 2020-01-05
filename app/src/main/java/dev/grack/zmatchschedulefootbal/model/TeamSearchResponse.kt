package dev.grack.zmatchschedulefootbal.model

import com.google.gson.annotations.SerializedName

data class TeamSearchResponse(
        @SerializedName("teams") var teams: List<TeamSearch>
)