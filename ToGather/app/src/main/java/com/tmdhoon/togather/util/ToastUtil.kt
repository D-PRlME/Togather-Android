package com.tmdhoon.togather.util

import android.content.Context
import android.widget.Toast

fun printToast(context: Context?, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun error(context: Context?) {
    Toast.makeText(context, "관리자에게 문의바랍니다", Toast.LENGTH_SHORT).show()
}