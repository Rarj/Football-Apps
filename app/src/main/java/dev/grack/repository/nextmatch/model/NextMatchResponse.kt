package dev.grack.repository.nextmatch.model

import com.google.gson.annotations.SerializedName

class NextMatchResponse(
      @SerializedName("events")
      var events: List<Event>?
)