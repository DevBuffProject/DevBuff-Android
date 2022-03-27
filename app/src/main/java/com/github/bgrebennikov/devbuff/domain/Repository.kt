package com.github.bgrebennikov.devbuff.domain

import com.github.bgrebennikov.devbuff.data.remote.models.auth.AuthConfig
import com.github.bgrebennikov.devbuff.data.local.SimpleIdeaModel
import com.github.bgrebennikov.devbuff.data.remote.models.explore.IdeaDetailsModel

interface Repository {

    suspend fun authGithubUser(code: String) : AuthConfig

    suspend fun getIdeas(page : Int) : List<SimpleIdeaModel>

    suspend fun getIdeaById(_id : String) : IdeaDetailsModel

}