package com.github.bgrebennikov.devbuff.di.modules

import android.app.Application
import com.github.bgrebennikov.devbuff.common.STAGING_URL
import com.github.bgrebennikov.devbuff.common.ServerErrorCodes
import com.github.bgrebennikov.devbuff.data.dataStore.DataStoreHelper
import com.github.bgrebennikov.devbuff.data.remote.ApiService
import com.github.bgrebennikov.devbuff.data.remote.TokenService
import com.github.bgrebennikov.devbuff.data.remote.UserApiService
import com.github.bgrebennikov.devbuff.network.UserServiceInterceptor
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.runBlocking
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpCache(application: Application) : Cache{
        val size = 10 * 1024 * 1024
        return Cache(
            application.cacheDir, size.toLong()
        )
    }



    @Singleton
    @Provides
    fun provideUserClient(interceptor: UserServiceInterceptor) : OkHttpClient{

        val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(logger)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService() : ApiService {

        val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(logger).build()

        val retrofit = Retrofit.Builder()
            .baseUrl(STAGING_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(
                GsonBuilder()
                    .setDateFormat("yyyy-MM-dd")
                    .create()
            ))
            .build()

        return retrofit.create(ApiService::class.java)
    }


    @Singleton
    @Provides
    fun provideUserApiService(userClient: OkHttpClient): UserApiService{

        val retrofit = Retrofit.Builder()
            .baseUrl(STAGING_URL)
            .client(userClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(UserApiService::class.java)

    }

    @Singleton
    @Provides
    fun provideTokenService() : TokenService{
        val retrofit = Retrofit.Builder()
            .baseUrl(STAGING_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(TokenService::class.java)
    }


}