package com.github.bgrebennikov.devbuff.data.remote.models.explore

import com.google.gson.annotations.SerializedName

enum class IdeaStatus(
    val value: String
) {
    WAITING_FULL_TEAM("WAITING_FULL_TEAM"),
    PUBLISH("PUBLISH")

}