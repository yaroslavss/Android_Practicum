package com.yara.feature_news

import com.yara.core.domain.repository.CategoriesRepository
import com.yara.core.domain.usecase.FilterEventsByTitleUseCase
import com.yara.core.domain.usecase.GetAllEventsWithCategoriesUseCase
import com.yara.core.domain.usecase.GetEventByIdUseCase
import com.yara.core.domain.usecase.GetEventsByCategoriesUseCase
import com.yara.core.domain.usecase.UpdateEventSetReadUseCase
import com.yara.feature_news.ui.NewsViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TestViewModelModule {
    @Singleton
    @Provides
    fun bindNewsViewModelFactory(
        categoriesRepository: CategoriesRepository,
        getAllEventsWithCategoriesUseCase: GetAllEventsWithCategoriesUseCase,
        getEventsByCategoriesUseCase: GetEventsByCategoriesUseCase,
        filterEventsByTitleUseCase: FilterEventsByTitleUseCase,
        updateEventSetReadUseCase: UpdateEventSetReadUseCase,
        getEventByIdUseCase: GetEventByIdUseCase,
    ): NewsViewModelFactory = NewsViewModelFactory(
        categoriesRepository,
        getAllEventsWithCategoriesUseCase,
        getEventsByCategoriesUseCase,
        filterEventsByTitleUseCase,
        updateEventSetReadUseCase,
        getEventByIdUseCase,
    )
}