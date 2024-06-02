package com.yara.core.di.module

import android.content.Context
import androidx.room.Room
import com.yara.core.data.db.HelpDatabase
import com.yara.core.utils.Constants.HELP_DATABASE
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideHelpDao(context: Context) =
        Room.databaseBuilder(
            context,
            HelpDatabase::class.java,
            HELP_DATABASE
        ).build().helpDao()
}