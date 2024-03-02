package com.yara.android_practicum.utils

fun interface Mapper<in A, out B> {

    fun map(type: A?): B
}