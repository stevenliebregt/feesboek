package com.stevenliebregt.feesboek.data.exposed

import com.stevenliebregt.feesboek.data.exposed.table.UsersTable
import com.stevenliebregt.feesboek.domain.entity.User
import com.stevenliebregt.feesboek.usecase.repository.UserRepository
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import javax.sql.DataSource

class ExposedUserRepository(dataSource: DataSource) : Repository(dataSource), UserRepository {
    override fun findAllUsers(): List<User> = transaction(connection) { UsersTable.selectAll().map { UsersTable.toDomain(it) } }
}