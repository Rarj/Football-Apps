package dev.grack.repository.team.model

import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("strTeamBadge")
    var strTeamBadge: String?
)