package com.github.bgrebennikov.devbuff.data.dataStore

import com.github.bgrebennikov.devbuff.data.remote.models.auth.AuthConfig

interface DataStoreHelper {

    suspend fun getLoggedInStatus(): Boolean
    fun getAccessToken(): String?
    fun getRefreshToken(): String?

    fun setAccessToken(token: String)
    fun setRefreshToken(refreshToken: String)

    fun storeUserModel(config: AuthConfig)
    fun clearAll()

}