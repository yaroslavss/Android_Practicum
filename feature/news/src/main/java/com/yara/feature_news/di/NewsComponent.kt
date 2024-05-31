package com.yara.feature_news.di

import android.content.Context
import com.yara.core.di.module.DatabaseModule
import com.yara.core.di.module.RemoteModule
import com.yara.core.di.module.RepositoryModule
import com.yara.feature_news.ui.NewsViewModel
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
interface NewsComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): NewsComponent
    }

    fun inject(newsViewModel: NewsViewModel)
}