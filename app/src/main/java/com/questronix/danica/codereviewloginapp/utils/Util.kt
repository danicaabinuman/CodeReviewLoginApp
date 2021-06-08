package com.questronix.danica.codereviewloginapp.utils

import android.content.res.AssetManager

object Util {
    fun AssetManager.readFile(fileName: String) = open(fileName)
        .bufferedReader()
        .use { it.readText() }
}