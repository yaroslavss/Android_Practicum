package com.yara.android_practicum.data.mapper

import android.annotation.SuppressLint
import com.yara.android_practicum.App
import com.yara.android_practicum.data.model.CategoryAPI
import com.yara.android_practicum.domain.model.Category

@SuppressLint("DiscouragedApi")
private fun createCategoryFromAPI(category: CategoryAPI): Category {
    val context = App.instance
    val iconId = context.resources.getIdentifier(category.image, "drawable", context.packageName)
    return Category(
        id = category.id,
        name = category.name,
        icon = iconId,
    )
}

fun CategoryAPI.toDomainModel() = createCategoryFromAPI(this)

fun List<CategoryAPI>.toDomainModelList() = this.map { createCategoryFromAPI(it) }