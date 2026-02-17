package com.example.moviz.utils

import java.text.SimpleDateFormat
import java.util.Locale

fun String.toEpoch(currentFormat: String = "yyyy-MM-dd"): Long? {
    val format = SimpleDateFormat(currentFormat, Locale.US)

    val date = format.parse(this)
    return date?.time
}
