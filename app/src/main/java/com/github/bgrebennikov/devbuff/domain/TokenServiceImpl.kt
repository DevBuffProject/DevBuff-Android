package com.github.bgrebennikov.devbuff.domain

import com.github.bgrebennikov.devbuff.data.ApiResponse
import com.github.bgrebennikov.devbuff.data.remote.TokenService
import com.github.bgrebennikov.devbuff.data.remote.models.auth.AuthConfig
import com.github.bgrebennikov.devbuff.data.remote.models.auth.TokenModel
import javax.inject.Inject

class TokenServiceImpl @Inject constructor(
    private val tokenService: TokenService
) : TokenServiceRepository {
    override suspend fun updateToken(accessToken: String, refreshToken: String?): ApiResponse<AuthConfig> {


        val result = try {
            ApiResponse.success(
                data = tokenService.updateToken(
                    authHeader = "Bearer $accessToken",
                    refreshToken = refreshToken!!
                )
            )
        } catch (e : Exception){
            ApiResponse.error(
                data = null,
                message = e.localizedMessage ?: "Error Occurred!"
            )
        }

        return result

    }
}