package com.stevenliebregt.feesboek.data.exposed

import com.stevenliebregt.feesboek.data.exposed.table.UsersTable
import com.stevenliebregt.feesboek.domain.entity.User
import com.stevenliebregt.feesboek.usecase.repository.UserRepository
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import javax.sql.DataSource

class ExposedUserRepository(dataSource: DataSource) : Repository(dataSource), UserRepository {
    override fun all(): List<User> = transaction(connection) { UsersTable.selectAll().map { UsersTable.toDomain(it) } }

    override fun findByEmail(email: String): User? {
        TODO("Not yet implemented")
    }

    override fun add(entity: User): User {
        TODO("Not yet implemented")
    }

    override fun update(entity: User): User {
        TODO("Not yet implemented")
    }
}