package dev.grack.zmatchschedulefootbal.model

import com.google.gson.annotations.SerializedName

data class EventSearchRespone(
        @SerializedName("event") var event: List<EventSearch>
)