package com.yara.feature_help.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.yara.core.domain.model.Categories
import com.yara.core.domain.repository.CategoriesRepository
import com.yara.core.domain.usecase.GetAllCategoriesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class CategoriesUiState {
    data object Loading : CategoriesUiState()
    data class Success(val categories: Categories) : CategoriesUiState()
    data class Error(val message: String) : CategoriesUiState()
}

class HelpViewModelFactory(
    private val categoriesRepository: CategoriesRepository,
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase,
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        HelpViewModel(categoriesRepository, getAllCategoriesUseCase) as T
}

class HelpViewModel(
    private val categoriesRepository: CategoriesRepository,
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase,
) : ViewModel() {

    private val scope = viewModelScope

    private val _uiState = MutableStateFlow<CategoriesUiState>(CategoriesUiState.Loading)
    val uiState: StateFlow<CategoriesUiState> = _uiState

    init {
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