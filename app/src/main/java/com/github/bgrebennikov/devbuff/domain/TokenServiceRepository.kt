package com.github.bgrebennikov.devbuff.domain

import com.github.bgrebennikov.devbuff.data.ApiResponse
import com.github.bgrebennikov.devbuff.data.remote.models.auth.AuthConfig
import com.github.bgrebennikov.devbuff.data.remote.models.auth.TokenModel

interface TokenServiceRepository {

    suspend fun updateToken(accessToken : String, refreshToken: String?) : ApiResponse<AuthConfig>?

}