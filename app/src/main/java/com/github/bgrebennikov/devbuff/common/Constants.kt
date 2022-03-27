package com.github.bgrebennikov.devbuff.common

const val TAG = "MESSAGE"

const val USER_ACCESS_TOKEN = "user_access_token"
const val USER_REFRESH_TOKEN = "user_refresh_token"
const val USER_TOKEN_EXPIRES_IN = "user_token_expires_in"

const val BASE_URL = "https://api.devbuff.com/"
const val STAGING_URL = "https://api-staging.devbuff.com/"

// for execute user profile image, put UserID to end of this string
const val BASE_IMAGE_URL = "https://api.devbuff.com/photo/profile"
const val STAGED_BASE_IMAGE_URL = "https://api-staging.devbuff.com/photo/profile"

const val AUTHORIZATION_HEADER = "Authorization"

object ServerErrorCodes{
    const val UNAUTHORIZED = 401
}