package com.yara.android_practicum.data.util

import com.yara.android_practicum.data.model.CategorySerialized
import com.yara.android_practicum.utils.AssetReader
import com.yara.android_practicum.utils.JsonDeserializer
import java.io.InputStream

class CategoryAssetReader(private val jsonDeserializer: JsonDeserializer<CategorySerialized> = CategoryDeserializer) :
    AssetReader<CategorySerialized> {

    @Throws(Exception::class)
    override fun readOne(input: InputStream): CategorySerialized {
        val inputStr = input.bufferedReader().use { it.readText() }
        return jsonDeserializer.deserializeOne(inputStr)
    }

    @Throws(Exception::class)
    override fun readList(input: InputStream): List<CategorySerialized> {
        val inputStr = input.bufferedReader().use { it.readText() }
        return jsonDeserializer.deserializeList(inputStr)
    }
}