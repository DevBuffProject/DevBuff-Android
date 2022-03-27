package com.github.bgrebennikov.devbuff.domain

import com.github.bgrebennikov.devbuff.data.remote.ApiService
import com.github.bgrebennikov.devbuff.data.local.SimpleIdeaModel
import com.github.bgrebennikov.devbuff.data.mappers.MapperIdeas
import com.github.bgrebennikov.devbuff.data.remote.models.auth.AuthConfig
import com.github.bgrebennikov.devbuff.data.remote.models.explore.IdeaDetailsModel
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : Repository {

    private val mapperIdeas = MapperIdeas()

    override suspend fun authGithubUser(code: String): AuthConfig {
        return apiService.githubOauth(code, "github_oauth")
    }

    override suspend fun getIdeas(page: Int): List<SimpleIdeaModel> {
        return mapperIdeas.mapToSimpleIdeasList(apiService.getIdeas(page).ideas)
    }

    override suspend fun getIdeaById(_id: String): IdeaDetailsModel {
        return apiService.getIdeaById(_id)
    }


}