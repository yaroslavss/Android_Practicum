package com.yara.core.di

import android.content.Context
import com.yara.core.di.module.DatabaseModule
import com.yara.core.di.module.RemoteModule
import com.yara.core.di.module.RepositoryModule
import com.yara.core.ui.news.NewsViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RemoteModule::class,
        DatabaseModule::class,
        RepositoryModule::class,
    ]
)
interface CoreComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): CoreComponent
    }

    fun inject(newsViewModel: NewsViewModel)
}