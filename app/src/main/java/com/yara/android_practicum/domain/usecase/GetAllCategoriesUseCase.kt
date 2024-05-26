package com.yara.android_practicum.domain.usecase

import com.yara.core.data.mapper.toEntityList
import com.yara.core.domain.model.Categories
import com.yara.core.domain.repository.CategoriesRepository
import com.yara.core.utils.Resource
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