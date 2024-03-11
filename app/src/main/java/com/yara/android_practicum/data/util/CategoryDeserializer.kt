package com.yara.android_practicum.data.util

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import com.yara.android_practicum.data.model.CategorySerialized
import com.yara.android_practicum.utils.JsonDeserializer

object CategoryDeserializer : JsonDeserializer<CategorySerialized> {

    private var gson: Gson

    init {
        val gsonBuilder = GsonBuilder()
        gson = gsonBuilder.create()
    }

    @Throws(JsonSyntaxException::class)
    override fun deserializeOne(json: String): CategorySerialized {
        return gson.fromJson(json, CategorySerialized::class.java)
    }

    override fun deserializeList(json: String): List<CategorySerialized> {
        val typeToken = object : TypeToken<List<CategorySerialized>>() {}.type
        return gson.fromJson(json, typeToken)
    }
}