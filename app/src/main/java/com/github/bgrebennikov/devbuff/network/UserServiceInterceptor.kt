package com.github.bgrebennikov.devbuff.network

import android.app.Application
import android.content.Intent
import android.util.Log
import com.github.bgrebennikov.devbuff.common.AUTHORIZATION_HEADER
import com.github.bgrebennikov.devbuff.common.ServerErrorCodes
import com.github.bgrebennikov.devbuff.common.TAG
import com.github.bgrebennikov.devbuff.data.dataStore.DataStoreHelper
import com.github.bgrebennikov.devbuff.data.local.explore.Status
import com.github.bgrebennikov.devbuff.domain.TokenServiceRepository
import com.github.bgrebennikov.devbuff.presentation.ui.GuestActivity
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.HttpException
import javax.inject.Inject

class UserServiceInterceptor @Inject constructor(
    private val dataStoreHelper: DataStoreHelper,
    private val application: Application,
    private val tokenService: TokenServiceRepository
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        synchronized(this){

            val accessToken = dataStoreHelper.getAccessToken()
            val refreshToken = dataStoreHelper.getRefreshToken()

            val originalRequest = chain.request()
            var authRequest = originalRequest.newBuilder()
                .addHeader(AUTHORIZATION_HEADER, "Bearer $accessToken")
                .build()

            val initialResponse = chain.proceed(authRequest)

            if(initialResponse.code == ServerErrorCodes.UNAUTHORIZED){

                runBlocking {
                    try {
                        val tokenResponse = tokenService.updateToken(
                            accessToken = accessToken!!,
                            refreshToken = refreshToken
                        )

                        tokenResponse?.let {
                            when(it.status){
                                Status.SUCCESS -> {
                                    it.data.let { authConfig ->
                                        dataStoreHelper.storeUserModel(authConfig!!)
                                        authRequest = originalRequest.newBuilder()
                                            .header(AUTHORIZATION_HEADER, "Bearer ${authConfig.access_token}")
                                            .build()
                                        Log.i(TAG, "intercept: Access token updated")
                                    }
                                }
                                Status.ERROR ->{
                                    dataStoreHelper.clearAll()
                                    application.startActivity(
                                        Intent(
                                            application,
                                            GuestActivity::class.java
                                        ).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                    )
                                }
                                Status.LOADING ->{

                                }
                            }
                        }

                    } catch (e : HttpException){
                        e.printStackTrace()
                        Log.i(TAG, "intercept: ${e.localizedMessage}")

                    }
                }



            }


            return chain.proceed(authRequest)

        }

    }
}
