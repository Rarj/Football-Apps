package dev.grack.zmatchschedulefootbal.model

import com.google.gson.annotations.SerializedName

data class EventsResponse(
        @SerializedName("events") var events: List<Event>
)