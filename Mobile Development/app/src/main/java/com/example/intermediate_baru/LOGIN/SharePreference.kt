package com.example.intermediate_baru.LOGIN

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.nio.file.Paths.get


class SharePreference(private val context: Context) {

    companion object {
        const val KEY_LOGIN = " berhasil_masuk"
        const val KEY_TOKEN = "token"
    }

    private val preference:SharedPreferences = context.getSharedPreferences("pref_name", Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = preference.edit()

    var berhasil_masuk: Boolean
    get() = preference.getBoolean(KEY_LOGIN, false)
    set(value) = editor.putBoolean(KEY_LOGIN, value).apply()

    var token: String?
    get() = preference.getString(KEY_TOKEN, null)
    set(value) = editor.putString(KEY_TOKEN, value).apply()

    fun clearUser(){
        editor.clear().apply()
    }


}
