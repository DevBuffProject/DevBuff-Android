package com.github.bgrebennikov.devbuff.data.dataStore

interface DataStoreRepository {

    suspend fun putString(key : String, value: String)
    suspend fun putInt(key : String, value: Int)

    suspend fun getString(key : String) : String?
    suspend fun getInt(key : String) : Int?

    suspend fun putLong(key : String, value: Long)
    suspend fun getLong(key: String) : Long?

    suspend fun clearAll()
    suspend fun clear(key: String)


}