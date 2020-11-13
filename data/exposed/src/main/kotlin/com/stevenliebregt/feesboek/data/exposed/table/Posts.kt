package com.stevenliebregt.feesboek.data.exposed.table

import com.stevenliebregt.feesboek.domain.entity.Post
import com.stevenliebregt.feesboek.domain.entity.User
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.jodatime.date
import org.joda.time.DateTime

object Posts : IntIdTable() {
    val title: Column<String> = varchar("title", 255)
    val body: Column<String> = text("body")
    val createdAt: Column<DateTime> = date("createdAt")
    val updatedAt: Column<DateTime?> = date("updatedAt").nullable()
    val authorId: Column<Int> = integer("authorId")

    fun toDomain(row: ResultRow, author: User): Post = Post(
            row[id].value,
            row[title],
            row[body],
            row[createdAt],
            row[updatedAt],
            author
    )
}