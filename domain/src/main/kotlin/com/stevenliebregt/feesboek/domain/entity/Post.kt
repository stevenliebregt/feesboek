package com.stevenliebregt.feesboek.domain.entity

import java.util.*

data class Post(
        private val id: Int,
        private val title: String,
        private val content: String,
        private val createdAt: Date,
        private val updatedAt: Date
)