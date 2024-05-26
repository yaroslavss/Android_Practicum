package com.yara.feature_help.di

import android.content.Context
import com.yara.core.di.module.DatabaseModule
import com.yara.core.di.module.RemoteModule
import com.yara.core.di.module.RepositoryModule
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
interface HelpComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): HelpComponent
    }
}