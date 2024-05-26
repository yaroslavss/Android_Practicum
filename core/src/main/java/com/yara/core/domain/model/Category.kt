package com.yara.core.domain.model

typealias Categories = List<Category>

data class Category(
    val id: Int,
    val name: String,
    val icon: String,
)