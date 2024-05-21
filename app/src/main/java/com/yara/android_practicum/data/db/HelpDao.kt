package com.yara.android_practicum.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.yara.android_practicum.data.db.entity.CategoryEntity
import com.yara.android_practicum.data.db.entity.EventEntity
import com.yara.android_practicum.data.db.entity.EventUpdateIsUnreadEntity
import com.yara.android_practicum.data.db.entity.relation.EventCategoryCrossRef
import com.yara.android_practicum.data.db.entity.relation.EventWithCategories
import com.yara.android_practicum.utils.Constants.CATEGORIES_TABLE
import com.yara.android_practicum.utils.Constants.EVENTS_CATEGORIES_TABLE
import com.yara.android_practicum.utils.Constants.EVENTS_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface HelpDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategoryList(categories: List<CategoryEntity>): Array<Long>

    @Query("SELECT * FROM $CATEGORIES_TABLE")
    fun getCategories(): Flow<List<CategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEventList(events: List<EventEntity>): Array<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEventCategoryCrossRef(eventCategoryCrossRef: EventCategoryCrossRef)

    @Update(entity = EventEntity::class)
    suspend fun updateEventIsUnread(event: EventUpdateIsUnreadEntity)

    @Query("SELECT * FROM $EVENTS_TABLE")
    fun getEvents(): Flow<List<EventEntity>>

    @Transaction
    @Query("SELECT * FROM $EVENTS_TABLE")
    fun getEventsWithCategories(): Flow<List<EventWithCategories>>

    @Transaction
    @Query(
        """
        SELECT DISTINCT * FROM $EVENTS_TABLE
                LEFT JOIN $EVENTS_CATEGORIES_TABLE ON $EVENTS_TABLE.id = $EVENTS_CATEGORIES_TABLE.event_id
                WHERE category_id IN (:categories)
                GROUP BY $EVENTS_TABLE.id
    """
    )
    fun getEventsByCategories(categories: Array<Int>): Flow<List<EventEntity>>

    @Query("SELECT * FROM $EVENTS_TABLE WHERE title LIKE :strToFind || '%'")
    fun filterEventsByTitle(strToFind: String): Flow<List<EventEntity>>
}