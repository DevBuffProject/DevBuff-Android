package com.github.bgrebennikov.devbuff.data.remote.models.explore

import com.google.gson.annotations.SerializedName

data class IdeasResponse(
    @SerializedName("perPage")
    val perPage: Int,

    @SerializedName("ideas")
    val ideas : List<IdeaModel>
)