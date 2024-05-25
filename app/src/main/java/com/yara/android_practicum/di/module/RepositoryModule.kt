package com.yara.android_practicum.di.module

import com.yara.core.data.api.RemoteAPI
import com.yara.core.data.db.HelpDao
import com.yara.android_practicum.data.model.CategorySerialized
import com.yara.android_practicum.data.model.EventSerialized
import com.yara.android_practicum.data.repository.CategoriesRepositoryImpl
import com.yara.android_practicum.data.repository.EventsRepositoryImpl
import com.yara.android_practicum.domain.repository.CategoriesRepository
import com.yara.android_practicum.domain.repository.EventsRepository
import com.yara.android_practicum.utils.AssetReader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideCategoriesRepository(
        assetDataSource: AssetReader<CategorySerialized>,
        remoteAPI: RemoteAPI,
        helpDao: HelpDao,
    ): CategoriesRepository = CategoriesRepositoryImpl(assetDataSource, remoteAPI, helpDao)

    @Provides
    @Singleton
    fun provideEventsRepository(
        assetDataSource: AssetReader<EventSerialized>,
        remoteAPI: RemoteAPI,
        helpDao: HelpDao,
    ): EventsRepository = EventsRepositoryImpl(assetDataSource, remoteAPI, helpDao)
}