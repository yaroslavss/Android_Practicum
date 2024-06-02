package com.yara.android_practicum.di.module

import com.yara.core.domain.repository.CategoriesRepository
import com.yara.core.domain.usecase.FilterEventsByTitleUseCase
import com.yara.core.domain.usecase.GetAllCategoriesUseCase
import com.yara.core.domain.usecase.GetAllEventsWithCategoriesUseCase
import com.yara.core.domain.usecase.GetEventsByCategoriesUseCase
import com.yara.core.domain.usecase.UpdateEventSetReadUseCase
import com.yara.feature_help.ui.HelpViewModelFactory
import com.yara.feature_news.ui.NewsViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelFactoryModule {

    @Provides
    @Singleton
    fun provideHelpViewModelFactory(
        categoriesRepository: CategoriesRepository,
        getAllCategoriesUseCase: GetAllCategoriesUseCase,
    ): HelpViewModelFactory =
        HelpViewModelFactory(categoriesRepository, getAllCategoriesUseCase)

    @Provides
    @Singleton
    fun provideNewsViewModelFactory(
        categoriesRepository: CategoriesRepository,
        getAllEventsWithCategoriesUseCase: GetAllEventsWithCategoriesUseCase,
        getEventsByCategoriesUseCase: GetEventsByCategoriesUseCase,
        filterEventsByTitleUseCase: FilterEventsByTitleUseCase,
        updateEventSetReadUseCase: UpdateEventSetReadUseCase,
    ): NewsViewModelFactory = NewsViewModelFactory(
        categoriesRepository,
        getAllEventsWithCategoriesUseCase,
        getEventsByCategoriesUseCase,
        filterEventsByTitleUseCase,
        updateEventSetReadUseCase,
    )
}