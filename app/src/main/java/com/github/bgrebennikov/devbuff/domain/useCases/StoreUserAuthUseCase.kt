package com.github.bgrebennikov.devbuff.domain.useCases

import com.github.bgrebennikov.devbuff.common.USER_ACCESS_TOKEN
import com.github.bgrebennikov.devbuff.common.USER_REFRESH_TOKEN
import com.github.bgrebennikov.devbuff.common.USER_TOKEN_EXPIRES_IN
import com.github.bgrebennikov.devbuff.data.dataStore.DataStoreHelper
import com.github.bgrebennikov.devbuff.data.dataStore.DataStoreRepository
import com.github.bgrebennikov.devbuff.data.remote.models.auth.AuthConfig
import javax.inject.Inject

class StoreUserAuthUseCase @Inject constructor(
    private val dataStoreHelper: DataStoreHelper
) {

    suspend operator fun invoke(config: AuthConfig){
        dataStoreHelper.storeUserModel(config)
    }

}