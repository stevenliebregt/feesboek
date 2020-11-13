package com.stevenliebregt.feesboek.domain.entity

import org.joda.time.DateTime

data class Post(
    val id: Int,
    var title: String,
    var body: String,
    var createdAt: DateTime,
    var updatedAt: DateTime?,
    var author: User
) {
    class Builder {
        private var title: String = ""
        private var body: String = ""
        private var createdAt: DateTime = DateTime.now()
        private var updatedAt: DateTime? = null
        private lateinit var author: User

        fun title(title: String) = apply { this.title = title }
        fun body(body: String) = apply { this.body = body }
        fun createdAt(createdAt: DateTime) = apply { this.createdAt = createdAt }
        fun updatedAt(updatedAt: DateTime) = apply { this.updatedAt = updatedAt }
        fun author(author: User) = apply { this.author = author }

        fun build() = Post(-1, title, body, createdAt, updatedAt, author)
    }
}