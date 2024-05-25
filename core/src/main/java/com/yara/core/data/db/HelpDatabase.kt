package com.yara.core.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yara.core.data.db.Converters
import com.yara.core.data.db.HelpDao
import com.yara.core.data.db.entity.CategoryEntity
import com.yara.core.data.db.entity.EventEntity
import com.yara.core.data.db.entity.relation.EventCategoryCrossRef

@Database(
    entities = [
        CategoryEntity::class,
        EventEntity::class,
        EventCategoryCrossRef::class,
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class HelpDatabase : RoomDatabase() {

    abstract fun helpDao(): HelpDao
}