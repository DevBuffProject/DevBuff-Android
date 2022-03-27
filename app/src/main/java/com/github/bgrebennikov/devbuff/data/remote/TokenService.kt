package com.github.bgrebennikov.devbuff.data.remote

import com.github.bgrebennikov.devbuff.data.remote.models.auth.AuthConfig
import com.github.bgrebennikov.devbuff.data.remote.models.auth.TokenModel
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface TokenService {


    @POST("oAuth/update")
    suspend fun updateToken(
        @Header("Authorization") authHeader : String,
        @Query("refresh_token") refreshToken: String,
        @Query("grant_type") grantType: String? = "refresh_token"
    ) : AuthConfig

}