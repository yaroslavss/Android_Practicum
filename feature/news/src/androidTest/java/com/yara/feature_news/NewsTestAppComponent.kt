package com.yara.feature_news

import android.content.Context
import com.yara.core.di.module.DatabaseModule
import com.yara.core.di.module.RemoteModule
import com.yara.core.di.module.RepositoryModule
import com.yara.feature_news.di.NewsComponent
import com.yara.feature_news.ui.EventDetailsFragment
import com.yara.feature_news.ui.NewsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        TestRepoModule::class,
        TestViewModelModule::class
    ]
)
interface NewsTestAppComponent: NewsComponent {
    @Component.Factory
    interface Factory {
        fun create(): NewsTestAppComponent
    }
}