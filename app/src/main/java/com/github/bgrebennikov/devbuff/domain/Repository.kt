package com.github.bgrebennikov.devbuff.domain

import com.github.bgrebennikov.devbuff.data.remote.models.auth.AuthConfig
import com.github.bgrebennikov.devbuff.data.local.explore.MappedIdeaModel
import com.github.bgrebennikov.devbuff.data.local.explore.ideaDetails.MappedIdeaDetailsModel
import com.github.bgrebennikov.devbuff.data.remote.models.explore.ideaDetails.IdeaDetailsModel

interface Repository {

    suspend fun authGithubUser(code: String) : AuthConfig

    suspend fun getIdeas(page : Int) : List<MappedIdeaModel>

    suspend fun getIdeaById(_id : String) : MappedIdeaDetailsModel

}