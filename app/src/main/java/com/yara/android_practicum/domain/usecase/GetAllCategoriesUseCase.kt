package com.yara.android_practicum.domain.usecase

import com.yara.android_practicum.data.mapper.toEntityList
import com.yara.android_practicum.domain.repository.CategoriesRepository
import com.yara.android_practicum.ui.help.Categories
import com.yara.android_practicum.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCategoriesUseCase @Inject constructor(
    private val categoriesRepository: CategoriesRepository,
) {

    suspend operator fun invoke(): Flow<Categories> {
        when (val result = categoriesRepository.getCategories()) {
            is Resource.Success -> categoriesRepository.insertCategoryListIntoDB(result.data.toEntityList())
            else -> {}
        }

        return categoriesRepository.queryCategoriesFromDB()
    }
}