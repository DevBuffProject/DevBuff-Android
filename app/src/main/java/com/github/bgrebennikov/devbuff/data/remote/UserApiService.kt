package com.github.bgrebennikov.devbuff.data.remote

import com.github.bgrebennikov.devbuff.data.remote.models.user.UserProfileModel
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserApiService {

    @GET("profile")
    suspend fun getProfile() : UserProfileModel

    @POST("oauth/refresh")
    suspend fun refresh(
        @Query("refresh_token") refreshToken : String
    ) : String

}