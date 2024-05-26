package com.yara.android_practicum.ui.help

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yara.android_practicum.di.DaggerAppComponent
import com.yara.android_practicum.domain.usecase.GetAllCategoriesUseCase
import com.yara.core.App
import com.yara.core.domain.model.Categories
import com.yara.core.domain.repository.CategoriesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class CategoriesUiState {
    data object Loading : CategoriesUiState()
    data class Success(val categories: Categories) : CategoriesUiState()
    data class Error(val message: String) : CategoriesUiState()
}

class HelpViewModel : ViewModel() {

    @Inject
    lateinit var getAllCategoriesUseCase: GetAllCategoriesUseCase

    @Inject
    lateinit var categoriesRepository: CategoriesRepository

    private val scope = viewModelScope

    private val _uiState = MutableStateFlow<CategoriesUiState>(CategoriesUiState.Loading)
    val uiState: StateFlow<CategoriesUiState> = _uiState

    init {
        DaggerAppComponent.factory()
            .create(App.instance)
            .inject(this)

        scope.launch {
            queryCategories()
        }
    }

    private suspend fun queryCategories() = categoriesRepository.queryCategoriesFromDB()
        .collect { categories ->
            _uiState.value = CategoriesUiState.Success(categories)
        }

    suspend fun initCategories() = getAllCategoriesUseCase()
}