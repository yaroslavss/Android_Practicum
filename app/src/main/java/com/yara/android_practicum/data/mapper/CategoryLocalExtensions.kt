package com.yara.android_practicum.data.mapper

import com.yara.android_practicum.data.model.CategoryLocal
import com.yara.android_practicum.domain.model.Category

private fun createCategoryFromLocal(category: CategoryLocal) =
    Category(
        id = category.id,
        name = category.name,
        icon = category.icon,
    )

fun CategoryLocal.toDomainModel() = createCategoryFromLocal(this)

fun List<CategoryLocal>.toDomainModelList() = this.map { createCategoryFromLocal(it) }