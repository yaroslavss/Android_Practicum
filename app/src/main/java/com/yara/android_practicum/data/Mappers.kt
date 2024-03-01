package com.yara.android_practicum.data

import com.yara.android_practicum.data.model.CategoryLocal
import com.yara.android_practicum.domain.model.Category
import com.yara.android_practicum.utils.Mapper

object Mappers {

    object CategoryToDomainListMapper : Mapper<List<CategoryLocal>, List<Category>> {
        override fun map(type: List<CategoryLocal>?): List<Category> {
            return type?.map {
                Category(
                    name = it.name,
                    icon = it.icon,
                )
            } ?: listOf()
        }
    }
}