package dev.grack.repository.pastmatch.model


import com.google.gson.annotations.SerializedName

data class PastMatchResponse(
      @SerializedName("events")
      var events: List<Event>
)