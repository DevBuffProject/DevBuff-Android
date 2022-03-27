package com.github.bgrebennikov.devbuff.data.remote

import com.github.bgrebennikov.devbuff.data.remote.models.auth.AuthConfig
import com.github.bgrebennikov.devbuff.data.remote.models.explore.IdeaDetailsModel
import com.github.bgrebennikov.devbuff.data.remote.models.explore.IdeasResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("idea/?sortBy=lastUpdate")
    suspend fun getIdeas(
        @Query("page") page: Int
    ): IdeasResponse

    @POST("/oAuth")
    suspend fun githubOauth(
        @Query("code") code: String,
        @Query("grant_type") grant_type: String,
        @Query("client_type") clientType: String = "app"
    ): AuthConfig

    @GET("/idea/{id}")
    suspend fun getIdeaById(
        @Path("id") _id : String
    ) : IdeaDetailsModel

}