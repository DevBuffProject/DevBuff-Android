package com.github.bgrebennikov.devbuff.di.modules

import com.github.bgrebennikov.devbuff.domain.Repository
import com.github.bgrebennikov.devbuff.domain.RepositoryImpl
import com.github.bgrebennikov.devbuff.domain.TokenServiceImpl
import com.github.bgrebennikov.devbuff.domain.TokenServiceRepository
import com.github.bgrebennikov.devbuff.domain.user.UserRemoteRepository
import com.github.bgrebennikov.devbuff.domain.user.UserRemoteRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface ApiRepositoryModule {

    @Binds
    fun bindRepository(impl: RepositoryImpl) : Repository

    @Binds
    fun bindUserRemoteRepository(impl : UserRemoteRepositoryImpl) : UserRemoteRepository

    @Binds
    fun bindTokenServiceRepository(impl: TokenServiceImpl) : TokenServiceRepository


}