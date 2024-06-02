package com.yara.core.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yara.core.utils.Constants.CATEGORIES_TABLE

@Entity(tableName = CATEGORIES_TABLE)
data class CategoryEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo("name_en")
    val nameEn: String,
    @ColumnInfo("image")
    val image: String,
)