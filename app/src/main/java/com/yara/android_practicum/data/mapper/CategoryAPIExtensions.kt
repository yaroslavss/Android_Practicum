package com.yara.android_practicum.data.mapper

import com.yara.core.data.model.CategoryAPI
import com.yara.android_practicum.domain.model.Category

private fun createCategoryFromAPI(category: CategoryAPI): Category {
    return Category(
        id = category.id,
        name = category.name,
        icon = category.image,
    )
}

fun CategoryAPI.toDomainModel() = createCategoryFromAPI(this)

fun List<CategoryAPI>.toDomainModelList() = this.map { createCategoryFromAPI(it) }