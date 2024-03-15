package com.yara.android_practicum.utils

fun Set<Int>.containsAny(list: List<Int>): Boolean {
    list.forEach { el ->
        if (this.contains(el))
            return true
    }
    return false
}