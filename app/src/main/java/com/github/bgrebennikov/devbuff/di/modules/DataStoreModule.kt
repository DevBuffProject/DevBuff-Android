package com.github.bgrebennikov.devbuff.di.modules

import com.github.bgrebennikov.devbuff.data.dataStore.DataStoreHelper
import com.github.bgrebennikov.devbuff.data.dataStore.DataStoreHelperImpl
import com.github.bgrebennikov.devbuff.data.dataStore.DataStoreRepository
import com.github.bgrebennikov.devbuff.domain.DataStoreRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DataStoreModule {

    @Singleton
    @Binds
    fun bindsDataStore(impl: DataStoreRepositoryImpl): DataStoreRepository

    @Singleton
    @Binds
    fun bindsDataStoreHelper(impl: DataStoreHelperImpl) : DataStoreHelper

}