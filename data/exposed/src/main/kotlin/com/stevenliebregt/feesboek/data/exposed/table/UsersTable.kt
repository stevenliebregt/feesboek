package com.stevenliebregt.feesboek.data.exposed.table

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object UsersTable : IntIdTable() {
    val email: Column<String> = varchar("email", 255)
    val password: Column<String> = varchar("password", 255)
    val token: Column<String> = varchar("token", 255)
}