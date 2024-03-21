package com.yara.android_practicum.utils

sealed class Resource<T>(
    data: T? = null,
    message: String? = null
) {
    data class Success<T>(val data: T) : Resource<T>(data, null)
    data class Error<T>(
        val message: String?
    ) : Resource<T>(null, message)
 
    class Loading<T> : Resource<T>()
}