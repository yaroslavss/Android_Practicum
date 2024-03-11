package com.yara.android_practicum.data.mapper

import android.annotation.SuppressLint
import com.yara.android_practicum.App
import com.yara.android_practicum.data.model.CategorySerialized
import com.yara.android_practicum.domain.model.Category

@SuppressLint("DiscouragedApi")
private fun createCategoryFromSerialized(category: CategorySerialized): Category {
    val context = App.instance
    val iconId = context.resources.getIdentifier(category.icon, "drawable", context.packageName)
    return Category(
        name = category.name,
        icon = iconId,
    )
}

fun CategorySerialized.toDomainModel() = createCategoryFromSerialized(this)

fun List<CategorySerialized>.toDomainModelList() = this.map { createCategoryFromSerialized(it) }