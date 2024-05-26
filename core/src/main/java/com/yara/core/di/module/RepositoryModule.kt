package com.yara.core.di.module

import com.yara.core.data.api.RemoteAPI
import com.yara.core.data.db.HelpDao
import com.yara.core.data.repository.CategoriesRepositoryImpl
import com.yara.core.data.repository.EventsRepositoryImpl
import com.yara.core.domain.repository.CategoriesRepository
import com.yara.core.domain.repository.EventsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideCategoriesRepository(
        remoteAPI: RemoteAPI,
        helpDao: HelpDao,
    ): CategoriesRepository = CategoriesRepositoryImpl(remoteAPI, helpDao)

    @Provides
    @Singleton
    fun provideEventsRepository(
        remoteAPI: RemoteAPI,
        helpDao: HelpDao,
    ): EventsRepository = EventsRepositoryImpl(remoteAPI, helpDao)
}