package com.yara.android_practicum.data.mapper

import com.yara.core.data.db.entity.relation.EventWithCategories

private fun createEventFromEventWithCategoriesEntity(eventWithCategories: EventWithCategories) =
    eventWithCategories.event.toDomainModel()

fun EventWithCategories.toDomainModel() = createEventFromEventWithCategoriesEntity(this)

fun List<EventWithCategories>.toDomainModelList() = this.map { it.toDomainModel() }