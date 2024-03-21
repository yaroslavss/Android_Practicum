package com.yara.android_practicum.data.util

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import com.yara.android_practicum.data.model.EventSerialized
import com.yara.android_practicum.utils.JsonDeserializer

object EventDeserializer : JsonDeserializer<EventSerialized> {

    private var gson: Gson

    init {
        val gsonBuilder = GsonBuilder()
        gson = gsonBuilder.create()
    }

    @Throws(JsonSyntaxException::class)
    override fun deserializeOne(json: String): EventSerialized {
        return gson.fromJson(json, EventSerialized::class.java)
    }

    @Throws(JsonSyntaxException::class)
    override fun deserializeList(json: String): List<EventSerialized> {
        val typeToken = object : TypeToken<List<EventSerialized>>() {}.type
        return gson.fromJson(json, typeToken)
    }
}