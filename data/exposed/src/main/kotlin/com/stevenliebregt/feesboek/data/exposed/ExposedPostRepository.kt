package com.stevenliebregt.feesboek.data.exposed

import com.stevenliebregt.feesboek.data.exposed.table.Posts
import com.stevenliebregt.feesboek.data.exposed.table.Users
import com.stevenliebregt.feesboek.domain.entity.Post
import com.stevenliebregt.feesboek.usecase.repository.IPostRepository
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import javax.sql.DataSource

class ExposedPostRepository(dataSource: DataSource) : Repository(dataSource), IPostRepository {
    override fun all() = transaction(connection) {
        Posts
            .join(Users, JoinType.INNER, additionalConstraint = { Posts.authorId eq Users.id })
            .selectAll()
            .map { Posts.toDomain(it, Users.toDomain(it)) }
    }

    override fun findById(id: Int) = transaction(connection) {
        Posts
            .join(Users, JoinType.INNER, additionalConstraint = { Posts.authorId eq Users.id })
            .select { Posts.id eq id }
            .firstOrNull()
            ?.let { Posts.toDomain(it, Users.toDomain(it)) }
    }

    override fun add(entity: Post) = transaction(connection) {
        val id = Posts.insertAndGetId { row ->
            row[title] = entity.title
            row[body] = entity.body
            row[createdAt] = entity.createdAt
            row[authorId] = entity.author.id
        }.value

        findById(id)!!
    }

    override fun update(entity: Post) = transaction(connection) {
        Posts.update({ Posts.id eq entity.id }) { row ->
            row[title] = entity.title
            row[body] = entity.body
            row[updatedAt] = entity.updatedAt
            row[authorId] = entity.author.id
        }

        findById(entity.id)!!
    }
}