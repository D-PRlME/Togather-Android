package com.tmdhoon.togather.util

import android.content.Context
import android.content.Intent

fun <T> startIntent(context: Context, to: Class<T>) {
    context.startActivity(Intent(context, to))
}

