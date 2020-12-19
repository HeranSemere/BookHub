package com.binaryninja.readerhub.tools

import android.content.Context
import android.content.SharedPreferences

class SharedPref(c: Context) {
    private var preferences: SharedPreferences = c.getSharedPreferences(Constant.SHARED_PREFNAME, Context.MODE_PRIVATE)
    var editor: SharedPreferences.Editor
    fun getInt(key: String?, def: Int): Int {
        return preferences.getInt(key, def)
    }

    fun setInt(key: String?, value: Int) {
        editor.putInt(key, value)
        editor.apply()
    }

    fun getString(key: String?, def: String?): String? {
        return preferences.getString(key, def)
    }

    fun setString(key: String?, value: String?) {
        editor.putString(key, value)
        editor.apply()
    }

    fun getFloat(key: String?, def: Float): Float {
        return preferences.getFloat(key, def)
    }

    fun setFloat(key: String?, value: Float) {
        editor.putFloat(key, value)
        editor.apply()
    }

    fun getBool(key: String?, def: Boolean): Boolean {
        return preferences.getBoolean(key, def)
    }

    fun setBool(key: String?, value: Boolean) {
        editor.putBoolean(key, value)
        editor.apply()
    }

    init {
        editor = preferences.edit()
    }
}