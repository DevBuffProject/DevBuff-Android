package com.github.bgrebennikov.devbuff.data.dataStore

import com.github.bgrebennikov.devbuff.common.USER_ACCESS_TOKEN
import com.github.bgrebennikov.devbuff.common.USER_REFRESH_TOKEN
import com.github.bgrebennikov.devbuff.data.remote.models.auth.AuthConfig
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class DataStoreHelperImpl @Inject constructor(
    private val repositoryDs: DataStoreRepository
) : DataStoreHelper {



    override suspend fun getLoggedInStatus(): Boolean {
        return repositoryDs.getString(USER_ACCESS_TOKEN) != null
    }

    override fun getAccessToken(): String? {
        return runBlocking {
            repositoryDs.getString(USER_ACCESS_TOKEN)
        }
    }

    override fun getRefreshToken(): String? {
        return runBlocking {
            repositoryDs.getString(USER_REFRESH_TOKEN)
        }
    }

    override fun setAccessToken(token: String) {
        runBlocking {
            repositoryDs.putString(USER_ACCESS_TOKEN, token)
        }
    }

    override fun setRefreshToken(refreshToken: String) {
        runBlocking {
            repositoryDs.putString(USER_REFRESH_TOKEN, refreshToken)
        }
    }

    override fun storeUserModel(config: AuthConfig) {
        setAccessToken(config.access_token)
        setRefreshToken(config.refresh_token)

    }

    override fun clearAll() {
        runBlocking {
            repositoryDs.clearAll()
        }
    }


}