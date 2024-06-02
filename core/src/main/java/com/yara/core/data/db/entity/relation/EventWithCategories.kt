package com.yara.core.data.db.entity.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.yara.core.data.db.entity.CategoryEntity
import com.yara.core.data.db.entity.EventEntity
import com.yara.core.data.db.entity.relation.EventCategoryCrossRef

data class EventWithCategories(
    @Embedded val event: EventEntity,
    @Relation(
        parentColumn = "id",
        entity = CategoryEntity::class,
        entityColumn = "id",
        associateBy = Junction(
            value = EventCategoryCrossRef::class,
            parentColumn = "event_id",
            entityColumn = "category_id",
        ),
    )
    val categories: List<CategoryEntity>
)