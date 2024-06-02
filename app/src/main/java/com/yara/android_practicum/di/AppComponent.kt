package com.yara.android_practicum.di

import android.content.Context
import com.yara.android_practicum.di.module.ViewModelFactoryModule
import com.yara.android_practicum.ui.MainActivity
import com.yara.android_practicum.ui.SplashActivity
import com.yara.core.di.module.DatabaseModule
import com.yara.core.di.module.RemoteModule
import com.yara.core.di.module.RepositoryModule
import com.yara.feature_help.di.HelpComponent
import com.yara.feature_help.ui.HelpFragment
import com.yara.feature_news.di.NewsComponent
import com.yara.feature_news.ui.NewsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RemoteModule::class,
        DatabaseModule::class,
        RepositoryModule::class,
        ViewModelFactoryModule::class,
    ]
)
interface AppComponent : NewsComponent, HelpComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun injectSplashActivity(splashActivity: SplashActivity)

    fun injectMainActivity(mainActivity: MainActivity)

    override fun injectHelpFragment(helpFragment: HelpFragment)

    override fun injectNewsFragment(newsFragment: NewsFragment)
}