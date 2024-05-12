package com.yara.android_practicum.data.mapper

import com.yara.android_practicum.data.db.entity.CategoryEntity
import com.yara.android_practicum.data.model.CategoryAPI
import com.yara.android_practicum.domain.model.Category

fun createCategoryFromEntity(categoryEntity: CategoryEntity) =
    Category(
        id = categoryEntity.id,
        name = categoryEntity.name,
        icon = categoryEntity.image,
    )

fun CategoryEntity.toDomainModel() = createCategoryFromEntity(this)

fun List<CategoryEntity>.toDomainModelList() = this.map { createCategoryFromEntity(it) }

private fun createCategoryEnitityFromAPI(category: CategoryAPI): CategoryEntity =
    CategoryEntity(
        id = category.id,
        name = category.name,
        nameEn = category.nameEn,
        image = category.image,
    )

fun CategoryAPI.toEntity() = createCategoryEnitityFromAPI(this)

fun List<CategoryAPI>.toEntityList() = this.map { createCategoryEnitityFromAPI(it) }