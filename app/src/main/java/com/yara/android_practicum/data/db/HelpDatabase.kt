package com.yara.android_practicum.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yara.android_practicum.data.db.entity.CategoryEntity
import com.yara.android_practicum.data.db.entity.EventEntity
import com.yara.android_practicum.utils.Constants.HELP_DATABASE

@Database(
    entities = [
        CategoryEntity::class,
        EventEntity::class,
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
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