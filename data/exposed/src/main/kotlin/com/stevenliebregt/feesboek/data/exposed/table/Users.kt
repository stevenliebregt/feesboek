package com.stevenliebregt.feesboek.data.exposed.table

import com.stevenliebregt.feesboek.domain.entity.User
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow

object Users : IntIdTable() {
    val email: Column<String> = varchar("email", 255)
    val password: Column<String> = varchar("password", 255)
    val token: Column<String?> = varchar("token", 255).nullable()

    fun toDomain(row: ResultRow): User = User(
            row[id].value,
            row[email],
            row[password],
            row[token]
    )
}