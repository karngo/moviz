package com.example.moviz.utils

fun getYearFromDate(date: String): String {
    return date.split("-").first()
}
