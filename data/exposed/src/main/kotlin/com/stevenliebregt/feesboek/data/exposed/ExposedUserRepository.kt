package com.stevenliebregt.feesboek.data.exposed

import com.stevenliebregt.feesboek.data.exposed.table.UsersTable
import com.stevenliebregt.feesboek.domain.entity.User
import com.stevenliebregt.feesboek.usecase.repository.UserRepository
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import javax.sql.DataSource

class ExposedUserRepository(dataSource: DataSource) : Repository(dataSource), UserRepository {
    override fun all(): List<User> = transaction(connection) {
        UsersTable
                .selectAll()
                .map { UsersTable.toDomain(it) }
    }

    override fun findByEmail(email: String) = transaction(connection) {
        UsersTable
                .select { UsersTable.email eq email }
                .firstOrNull()
                ?.let { UsersTable.toDomain(it) }
    }

    override fun add(entity: User) = transaction(connection) {
        val id = UsersTable.insertAndGetId { row ->
            row[email] = entity.email
            row[password] = entity.password // TODO: Hash
        }.value

        UsersTable.toDomain(UsersTable.select { UsersTable.id eq id }.first())
    }

    override fun update(entity: User) = transaction(connection) {
        UsersTable.update ({ UsersTable.id eq entity.id }) { row ->
            row[email] = entity.email
            row[password] = entity.password // TODO: Hash
            row[token] = entity.token
        }

        entity
    }
}