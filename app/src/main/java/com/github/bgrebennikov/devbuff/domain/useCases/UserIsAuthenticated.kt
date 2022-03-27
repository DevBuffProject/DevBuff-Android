package com.github.bgrebennikov.devbuff.domain.useCases

import com.github.bgrebennikov.devbuff.common.USER_ACCESS_TOKEN
import com.github.bgrebennikov.devbuff.data.dataStore.DataStoreHelper
import com.github.bgrebennikov.devbuff.data.dataStore.DataStoreRepository
import javax.inject.Inject

class UserIsAuthenticated @Inject constructor(
    private val helper: DataStoreHelper
) {

    suspend operator fun invoke() : Boolean{
        return helper.getLoggedInStatus()
    }

}