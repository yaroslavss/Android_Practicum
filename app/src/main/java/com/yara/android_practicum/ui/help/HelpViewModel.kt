package com.yara.android_practicum.ui.help

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yara.android_practicum.App
import com.yara.android_practicum.data.repository.CategoriesRepositoryImpl
import com.yara.android_practicum.domain.model.Category
import com.yara.android_practicum.domain.usecase.GetAllCategoriesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

typealias Categories = List<Category>

data class CategoriesUiState(
    val categories: Categories = emptyList(),
    val isLoading: Boolean = true,
    val userMessage: String = "",
)

class HelpViewModel : ViewModel() {

    private val context = App.instance

    @Inject
    lateinit var getAllCategoriesUseCase: GetAllCategoriesUseCase

    @Inject
    lateinit var categoriesRepository: CategoriesRepositoryImpl

    private val scope = viewModelScope

    private val _uiState = MutableStateFlow(CategoriesUiState())
    val uiState: StateFlow<CategoriesUiState> = _uiState

    init {
        App.instance.dagger.inject(this)

        scope.launch {
            queryCategories()
        }
    }

    private suspend fun queryCategories() = categoriesRepository.queryCategoriesFromDB()
        .collect { categories ->
            _uiState.update {
                _uiState.value.copy(
                    categories = categories,
                    isLoading = false,
                )
            }
        }

    fun initCategories() = getAllCategoriesUseCase(scope)
}