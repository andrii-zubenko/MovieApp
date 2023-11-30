package com.andrii.movieapp.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatTimestamp(timestamp: String?): String {
    return try {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val date = timestamp?.toLongOrNull()?.let { Date(it) } ?: Date()
        dateFormat.format(date)
    } catch (e: Exception) {
        "N/A"
    }
}
