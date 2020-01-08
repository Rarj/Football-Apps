package dev.grack.repository.team.model


import com.google.gson.annotations.SerializedName

data class TeamResponse(
    @SerializedName("teams")
    var teams: List<Team?>?
)