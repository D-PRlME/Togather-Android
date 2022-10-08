package com.tmdhoon.togather.util

import android.content.SharedPreferences
import android.icu.number.IntegerWidth
import androidx.appcompat.app.AppCompatActivity

object SharedPreferencesUtil : AppCompatActivity() {
    private val sharedPreferences : SharedPreferences by lazy {
        getSharedPreferences("User", MODE_PRIVATE)
    }

    private val editor : SharedPreferences.Editor by lazy {
        sharedPreferences.edit()
    }

    private lateinit var result : String

    fun <T> put(key : String, value : T){
        when(value){
            (value is String) -> editor.putString(key, value.toString()).apply()
            (value is Int) -> editor.putInt(key, Integer.parseInt(value.toString())).apply()
            (value is Boolean) -> editor.putBoolean(key, value as Boolean)
        }
    }

    fun <T> get(key : String, value : T) : String {

        when(value){
            (value is String) -> result = sharedPreferences.getString(key, value.toString()).toString()
            (value is Int) -> result = sharedPreferences.getInt(key, Integer.parseInt(value.toString())) as String
            (value is Boolean) -> result = sharedPreferences.getBoolean(key, value as Boolean) as String
        }
        return result
    }

}