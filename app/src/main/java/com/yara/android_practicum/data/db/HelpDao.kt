package com.yara.android_practicum.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yara.android_practicum.data.db.entity.CategoryEntity
import com.yara.android_practicum.utils.Constants.CATEGORIES_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface HelpDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategoryList(categories: List<CategoryEntity>): Array<Long>

    @Query("SELECT * FROM $CATEGORIES_TABLE")
    fun getCategories(): Flow<List<CategoryEntity>>
}