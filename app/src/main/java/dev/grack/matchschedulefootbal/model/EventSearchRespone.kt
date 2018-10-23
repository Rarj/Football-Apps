package dev.grack.matchschedulefootbal.model

import com.google.gson.annotations.SerializedName

data class EventSearchRespone(
        @SerializedName("event") var event: List<EventSearch>
)