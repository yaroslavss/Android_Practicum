package com.yara.android_practicum.domain.usecase

import com.yara.android_practicum.data.repository.CategoriesRepositoryImpl
import com.yara.android_practicum.ui.help.Categories
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class GetAllCategoriesUseCase @Inject constructor(
    private val categoriesRepository: CategoriesRepositoryImpl,
) {

    operator fun invoke(isFirstRun: Int, scope: CoroutineScope): Flow<Categories> {
        if (isFirstRun > 0) {
            scope.launch {
                categoriesRepository.getCategories().collect { categories ->
                    categoriesRepository.insertCategoryListIntoDB(categories)
                }
            }
        }

        return categoriesRepository.queryCategoriesFromDB()
    }
}