package com.stevenliebregt.feesboek.data.exposed

import com.stevenliebregt.feesboek.data.exposed.table.Users
import com.stevenliebregt.feesboek.domain.entity.User
import com.stevenliebregt.feesboek.usecase.repository.IUserRepository
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import javax.sql.DataSource

class ExposedUserRepository(dataSource: DataSource) : Repository(dataSource), IUserRepository {
    override fun all(): List<User> = transaction(connection) {
        Users
                .selectAll()
                .map { Users.toDomain(it) }
    }

    override fun findByEmail(email: String) = transaction(connection) {
        Users
                .select { Users.email eq email }
                .firstOrNull()
                ?.let { Users.toDomain(it) }
    }

    override fun findById(id: Int) = transaction(connection) {
        Users
            .select { Users.id eq id }
            .firstOrNull()
            ?.let { Users.toDomain(it) }
    }

    override fun add(entity: User) = transaction(connection) {
        val id = Users.insertAndGetId { row ->
            row[email] = entity.email
            row[password] = entity.password // TODO: Hash
        }.value

        findById(id)!!
    }

    override fun update(entity: User) = transaction(connection) {
        Users.update ({ Users.id eq entity.id }) { row ->
            row[email] = entity.email
            row[password] = entity.password // TODO: Hash
            row[token] = entity.token
        }

        findById(entity.id)!!
    }
}