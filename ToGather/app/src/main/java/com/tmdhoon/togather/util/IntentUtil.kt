package com.tmdhoon.togather.util

import android.content.Context
import android.content.Intent

object IntentUtil {

    fun <T> startIntent(context: Context, to: Class<T>){
        context.startActivity(Intent(context, to))
    }
    fun <T:Class<T>> startIntentClearTop(context: Context, to: T){
        val intent = Intent(context, to)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        context.startActivity(intent)
    }

}