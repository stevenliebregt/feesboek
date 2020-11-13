package com.stevenliebregt.feesboek.domain.entity

import org.joda.time.DateTime

data class Post(
        val id: Int,
        val title: String,
        val body: String,
        val createdAt: DateTime,
        val updatedAt: DateTime?,
        val author: User
)