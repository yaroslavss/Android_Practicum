package com.yara.android_practicum.data.util

import com.yara.android_practicum.utils.AssetReader
import com.yara.android_practicum.utils.JsonDeserializer
import java.io.InputStream

class AssetReaderImpl<T>(private val jsonDeserializer: JsonDeserializer<T>) :
    AssetReader<T> {

    @Throws(Exception::class)
    override fun readOne(input: InputStream): T {
        val inputStr = input.bufferedReader().use { it.readText() }
        return jsonDeserializer.deserializeOne(inputStr)
    }

    @Throws(Exception::class)
    override fun readList(input: InputStream): List<T> {
        val inputStr = input.bufferedReader().use { it.readText() }
        return jsonDeserializer.deserializeList(inputStr)
    }
}