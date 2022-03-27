package com.github.bgrebennikov.devbuff.data.remote.models.user

import com.google.gson.annotations.SerializedName

data class UserSkills(
    @SerializedName("name")
    val name: String,

    @SerializedName("levelKnowledge")
    val levelKnowledge: String,

    @SerializedName("specializations")
    val specializations: List<UserSkillsSpecializations>,
    )
