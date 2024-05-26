package com.yara.android_practicum.di

import android.content.Context
import com.yara.android_practicum.ui.news.NewsViewModel
import com.yara.core.di.module.DatabaseModule
import com.yara.core.di.module.RemoteModule
import com.yara.core.di.module.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DatabaseModule::class,
        RemoteModule::class,
        RepositoryModule::class,
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(newsViewModel: NewsViewModel)
}