package com.example.booksearchexample.util

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import kotlinx.serialization.json.Json
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

inline fun <reified T : Any?> serializableType(
    isNullableAllowed: Boolean = false
) = object : NavType<T>(isNullableAllowed = isNullableAllowed) {
    override fun get(bundle: Bundle, key: String): T? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            bundle.getParcelable(key, T::class.java)
        } else {
            @Suppress("DEPRECATION") bundle.getParcelable(key)
        }
    }

    override fun parseValue(value: String): T {
        return Json.decodeFromString<T>(value.replace("+", " "))
    }

    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putParcelable(key, value as Parcelable)
    }

    override fun serializeAsValue(value: T): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            URLEncoder.encode(Json.encodeToString(value), StandardCharsets.UTF_8)
        } else {
            @Suppress("DEPRECATION")
            URLEncoder.encode(Json.encodeToString(value))
        }
    }
}
