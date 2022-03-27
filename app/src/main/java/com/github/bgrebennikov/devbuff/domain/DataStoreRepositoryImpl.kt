package com.github.bgrebennikov.devbuff.domain

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.github.bgrebennikov.devbuff.data.dataStore.DataStoreRepository
import com.github.bgrebennikov.devbuff.di.qualifiers.DataStorage
import kotlinx.coroutines.flow.first
import javax.inject.Inject


class DataStoreRepositoryImpl @Inject constructor(
    private val context: Application,
    @DataStorage private val dataStorageName: String
) : DataStoreRepository {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = dataStorageName)

    override suspend fun putString(key: String, value: String) {
        val pKey = stringPreferencesKey(key)
        with(context){
            dataStore.edit { prefs ->
                prefs[pKey] = value
            }
        }
    }

    override suspend fun putInt(key: String, value: Int) {
        with(context){
            dataStore.edit { prefs ->
                prefs[intPreferencesKey(key)] = value
            }
        }
    }

    override suspend fun getString(key: String): String? {
        val pKey = stringPreferencesKey(key)
        val data = context.dataStore.data.first()
        return data[pKey]
    }

    override suspend fun getInt(key: String): Int? =
        context.dataStore.data.first()[intPreferencesKey(key)]

    override suspend fun putLong(key: String, value: Long) {
        with(context.dataStore){
            edit { prefs ->
                prefs[longPreferencesKey(key)] = value
            }
        }
    }

    override suspend fun getLong(key: String): Long? =
        context.dataStore.data.first()[longPreferencesKey(key)]

    override suspend fun clearAll() {
        context.dataStore.edit { it.clear() }
    }

    override suspend fun clear(key: String) {
        TODO()
    }


}

