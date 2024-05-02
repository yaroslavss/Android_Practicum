package com.yara.android_practicum.data.db.entity

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yara.android_practicum.data.db.HelpDao
import com.yara.android_practicum.utils.Constants.HELP_DATABASE

@Database(
    entities = [
        CategoryEntity::class,
    ],
    version = 1
)
abstract class HelpDatabase : RoomDatabase() {

    abstract fun HelpDao(): HelpDao

    companion object {
        @Volatile
        private var INSTANCE: HelpDatabase? = null

        fun getInstance(context: Context): HelpDatabase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    HelpDatabase::class.java,
                    HELP_DATABASE
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}