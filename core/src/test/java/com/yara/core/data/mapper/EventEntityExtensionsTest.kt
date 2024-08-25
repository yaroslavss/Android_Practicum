package com.yara.core.data.mapper

import com.yara.core.test_data.EventEntityExtensionsTestData.testEventAPI
import com.yara.core.test_data.EventEntityExtensionsTestData.testEventDomainModel
import com.yara.core.test_data.EventEntityExtensionsTestData.testEventEntity
import org.junit.Assert.assertEquals
import org.junit.Test

class EventEntityExtensionsTest {

    @Test
    fun test_EventAPI_to_EventEntity_extensions() {
        /* Given */
        val eventAPI = testEventAPI

        /* When */
        val eventEntity = testEventEntity

        /* Then */
        assertEquals(eventEntity, eventAPI.toEntity())
    }

    @Test
    fun test_EventEntity_to_EventDomainModel_extensions() {
        /* Given */
        val eventEntity = testEventEntity

        /* When */
        val eventDomainModel = testEventDomainModel

        /* Then */
        assertEquals(eventDomainModel, eventEntity.toDomainModel())
    }
}